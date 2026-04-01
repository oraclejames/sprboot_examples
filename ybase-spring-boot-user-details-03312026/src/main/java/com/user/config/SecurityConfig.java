package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
            .csrf(csrf -> csrf.disable())   // Testing కి csrf off
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()   // అందరికీ access — testing!
            );
        return http.build();
    }
}