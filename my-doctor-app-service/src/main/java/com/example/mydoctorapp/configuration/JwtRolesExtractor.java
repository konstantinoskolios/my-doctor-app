package com.example.mydoctorapp.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtRolesExtractor {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLES = "roles";
    private static final String REALM_ACCESS_CLAIM = "realm_access";

    public static Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim(REALM_ACCESS_CLAIM);
        Collection<String> resourceRoles;

        if (realmAccess != null && (resourceRoles = (Collection<String>) realmAccess.get(ROLES)) != null)
            return resourceRoles.stream()
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                    .collect(Collectors.toSet());

        return Collections.emptySet();
    }

    private JwtRolesExtractor() {
    }
}
