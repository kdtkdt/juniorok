package com.juniorok.juniorok.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain pageRequestFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {auth.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();})
                .build();
    }
}
