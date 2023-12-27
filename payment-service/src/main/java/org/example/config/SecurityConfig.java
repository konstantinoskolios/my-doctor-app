package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    public static final String DOCTOR_APP_USER = "doctor-app-user";
    public static final String DOCTOR_APP_ADMIN = "doctor-app-admin";
    public static final String DOCTOR_APP_SUPER_USER = "doctor-app-super-user";

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

        serverHttpSecurity
                .authorizeExchange(ex -> ex
                        .pathMatchers("/**").hasAnyRole(DOCTOR_APP_USER,DOCTOR_APP_SUPER_USER,DOCTOR_APP_ADMIN)
                        .anyExchange().permitAll())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .oauth2ResourceServer(configurer -> configurer
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(new KeycloakJwtConverter())))
        ;

        return serverHttpSecurity.build();
    }

}


