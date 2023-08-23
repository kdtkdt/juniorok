package com.juniorok.juniorok.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import java.time.Duration;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final PersistentTokenRepository persistentTokenRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/img/**"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/favicon.ico"),
                new AntPathRequestMatcher("/error"));
    }

    @Bean
    SecurityFilterChain pageRequestFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth ->
                    {auth.requestMatchers(
                            new AntPathRequestMatcher("/"),
                            new AntPathRequestMatcher("/main"),
                            new RegexRequestMatcher("^(/post/list)(\\?.*)?", "GET"),
                            new RegexRequestMatcher("/post/\\d+", "GET")).permitAll();
                    auth.requestMatchers(
                            new AntPathRequestMatcher("/post/write", "GET"),
                            new AntPathRequestMatcher("/post", "POST")).hasAnyRole("ADMIN", "WRITER");
                    auth.requestMatchers(
                            new AntPathRequestMatcher("/mypage", "GET")).authenticated();
                    auth.requestMatchers(
                            new AntPathRequestMatcher("/adminpost", "GET"),
                            new AntPathRequestMatcher("/admin/deletePost/**", "GET"),
                            new AntPathRequestMatcher("/post/modify/**", "GET"),
                            new AntPathRequestMatcher("/post/update/**", "GET"),
                            new AntPathRequestMatcher("/admin/**", "GET"),
                            new AntPathRequestMatcher("/adminusers", "GET"),
                            new AntPathRequestMatcher("/admin/main", "GET"),
                            new AntPathRequestMatcher("/user/**", "GET"),
                            new AntPathRequestMatcher("/user/deleteUser/**", "GET"),
                            new AntPathRequestMatcher("/user/bulkDeleteUsers", "GET"),
                            new AntPathRequestMatcher("/user/authority", "GET"),
                            new AntPathRequestMatcher("/user/deleteauthority", "GET")).hasAnyRole("ADMIN", "WRITER");
                    auth.requestMatchers(
                            new AntPathRequestMatcher("/adminpost", "GET"),
                            new AntPathRequestMatcher("/admin/deletePost/**", "GET")).hasRole("ADMIN");
        })
                .oauth2Login(config -> {
                    config.authorizedClientService(oAuth2AuthorizedClientService);
                    config.loginPage("/oauth2/authorization/github");
                })
                .logout(config -> {
                    config.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    config.logoutSuccessUrl("/");
                })
                .rememberMe(config -> {
                    config.alwaysRemember(true);
                    config.key("juniorOk");
                    config.tokenValiditySeconds((int) Duration.ofDays(28).toSeconds());
                    config.tokenRepository(persistentTokenRepository);
                })
                .build();
    }


}
