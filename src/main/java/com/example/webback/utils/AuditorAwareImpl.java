package com.example.webback.utils;



import com.example.webback.business.entity.AuthorityEntity;
import com.example.webback.business.entity.UserEntity;
import com.example.webback.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<UserEntity> {
    private final UserService userService;

    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() instanceof AnonymousAuthenticationToken)
            return Optional.empty();
        OAuth2Authentication authentication = (OAuth2Authentication) securityContext.getAuthentication();
        if (authentication != null){
            Map<String, Object> mapDetails = userService.getAdditionInformation(authentication);
            UserEntity user = getUserFromMap(mapDetails);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public UserEntity getUserFromMap(Map<String, Object> map) {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(map.get("user_id").toString()));
        user.setUsername(((String) map.get("user_name")));
        user.setUserAuthorities(getSetAuthorityFromMap((LinkedHashMap)((ArrayList)map.get("userAuthorities")).get(0)));
        return user;
    }

    private Set<AuthorityEntity> getSetAuthorityFromMap(LinkedHashMap<String, Object> map){
        Set<AuthorityEntity> setAuthority = new HashSet<>();
        AuthorityEntity authority = new AuthorityEntity();
        authority.setId((Integer) map.get("id"));
        authority.setName((String) map.get("name"));
        authority.setDescription((String) map.get("description"));
        authority.setDateTimeCreate(getLocalDateTimeFromMap((LinkedHashMap) map.get("dateTimeCreate")));
        authority.setDateTimeModif(getLocalDateTimeFromMap((LinkedHashMap) map.get("dateTimeModif")));
        setAuthority.add(authority);
        return setAuthority;
    }

    private LocalDateTime getLocalDateTimeFromMap(LinkedHashMap<String, Object> map){
        return LocalDateTime.of((Integer) map.get("year"),
                (Integer) map.get("monthValue"),
                (Integer) map.get("dayOfMonth"),
                (Integer) map.get("hour"),
                (Integer) map.get("minute"),
                (Integer) map.get("second"),
                (Integer) map.get("nano"));
    }
}
