package com.example.webback.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Optional;


@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@RequiredArgsConstructor
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;

    private final DataSource dataSource;

    private final TokenStore tokenStore;

    private final JwtAccessTokenConverter accessTokenConverter;

    private final DefaultTokenServices defaultTokenServices;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .tokenEnhancer(accessTokenConverter)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/oauth/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/swagger-ui.html#/**").anonymous()
                    .antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/register").anonymous()
                    .antMatchers(HttpMethod.GET, "/api/v1/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers(HttpMethod.POST, "/api/v1/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers(HttpMethod.PATCH, "/api/v1/**").hasAnyAuthority("ADMIN", "USER")
                    .anyRequest()
                    .authenticated();
            

        }
    }


    @Configuration
    @Conditional(JwtKeyStoreCondition.class)
    @RequiredArgsConstructor
    protected static class JwtKeyStoreConfiguration implements ApplicationContextAware {

        private final AuthorizationServerProperties authorization;
        private ApplicationContext context;
        private final CustomJwtAccessTokenConverter converter;
        private final UserDetailsService userDetailsService;
        private final ClientDetailsService clientDetailsService;


        @Override
        public void setApplicationContext(ApplicationContext context) throws BeansException {
            this.context = context;
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices(TokenStore jwtTokenStore) {
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(jwtTokenStore);
            defaultTokenServices.setSupportRefreshToken(true);
            defaultTokenServices.setAccessTokenValiditySeconds(5454545);
            defaultTokenServices.setTokenEnhancer(accessTokenConverter());
            defaultTokenServices.setClientDetailsService(clientDetailsService);
            addUserDetailsService(defaultTokenServices, userDetailsService);
            return defaultTokenServices;
        }

        private void addUserDetailsService(DefaultTokenServices tokenServices, UserDetailsService userDetailsService) {
            if (userDetailsService != null) {
                PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
                provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(
                        userDetailsService));
                tokenServices
                        .setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
            }
        }

        @Bean
        @ConditionalOnMissingBean(TokenStore.class)
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }


        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            Assert.notNull(this.authorization.getJwt().getKeyStore(), "keyStore cannot be null");
            Assert.notNull(this.authorization.getJwt().getKeyStorePassword(), "keyStorePassword cannot be null");
            Assert.notNull(this.authorization.getJwt().getKeyAlias(), "keyAlias cannot be null");
            Resource keyStore = this.context.getResource(this.authorization.getJwt().getKeyStore());
            char[] keyStorePassword = this.authorization.getJwt().getKeyStorePassword().toCharArray();
            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, keyStorePassword);

            String keyAlias = this.authorization.getJwt().getKeyAlias();
            char[] keyPassword = Optional.ofNullable(
                    this.authorization.getJwt().getKeyPassword())
                    .map(String::toCharArray).orElse(keyStorePassword);
            converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword));
            return converter;
        }
    }


    private static class JwtKeyStoreCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context,
                                                AnnotatedTypeMetadata metadata) {
            ConditionMessage.Builder message = ConditionMessage
                    .forCondition("OAuth JWT KeyStore Condition");
            Environment environment = context.getEnvironment();
            String keyStore = environment
                    .getProperty("security.oauth2.authorization.jwt.key-store");
            if (StringUtils.hasText(keyStore)) {
                return ConditionOutcome
                        .match(message.foundExactly("provided key store location"));
            }
            return ConditionOutcome
                    .noMatch(message.didNotFind("provided key store location").atAll());
        }

    }

}
