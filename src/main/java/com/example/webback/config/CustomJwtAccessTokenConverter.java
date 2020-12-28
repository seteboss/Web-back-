package com.example.webback.config;


import com.example.webback.business.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInformationMap = new HashMap<>();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        additionalInformationMap.put("user_id", extractUserId(authentication));
        additionalInformationMap.put("first_name", user.getFirstName());
        additionalInformationMap.put("second_name", user.getSecondName());
        additionalInformationMap.put("userAuthorities", user.getAuthorities());
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(additionalInformationMap);
        return super.enhance(accessToken, authentication);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }

    private UUID extractUserId(OAuth2Authentication authentication) {

        return ((UserEntity) authentication.getUserAuthentication().getPrincipal()).getId();
    }
}