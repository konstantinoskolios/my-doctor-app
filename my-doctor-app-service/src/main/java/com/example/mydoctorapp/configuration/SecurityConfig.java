package com.example.mydoctorapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

import static com.example.mydoctorapp.configuration.JwtRolesExtractor.extractRoles;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String DOCTOR_APP_USER = "doctor-app-user";
    public static final String DOCTOR_APP_ADMIN = "doctor-app-admin";
    public static final String DOCTOR_APP_SUPER_USER = "doctor-app-super-user";

    @Value("${jwt.auth.converter.principal-attribute}")
    private String principalAttribute;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/", "/login", "/public").permitAll()
                        .requestMatchers( "/user/**").hasRole(DOCTOR_APP_USER)
                        .requestMatchers("/admin/**").hasRole(DOCTOR_APP_ADMIN)
                        .requestMatchers("/super-user/**").hasRole(DOCTOR_APP_SUPER_USER)
                        .requestMatchers(HttpMethod.GET,"/oauth2/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/login").defaultSuccessUrl("/"))
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService(@Autowired JwtDecoder jwtDecoder) {
        OidcUserService delegate = new OidcUserService();
        return (userRequest) -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);

            Jwt jwt = jwtDecoder.decode(userRequest.getAccessToken().getTokenValue());
            Collection<? extends GrantedAuthority> authorities = extractRoles(jwt);

            String nameAttributeKey = principalAttribute == null ? JwtClaimNames.SUB : principalAttribute;
            return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo(), nameAttributeKey);
        };
    }
}
