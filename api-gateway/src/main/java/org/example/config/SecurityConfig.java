package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

        serverHttpSecurity
                .authorizeExchange()
                .pathMatchers("/api/*/admin/**").hasRole("admin")
                .pathMatchers("/api/*/user/**").hasRole("user")
                .pathMatchers("/api/*/doctorapp/**").hasRole("user")
                .pathMatchers("/api/*/public/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(new KeycloakJwtConverter());

        serverHttpSecurity.csrf().disable();
        return serverHttpSecurity.build();
    }
}


