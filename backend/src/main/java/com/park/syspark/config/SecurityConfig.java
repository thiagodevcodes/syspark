package com.park.syspark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
                                 .requestMatchers(new AntPathRequestMatcher("/roles/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll()
                                .anyRequest().authenticated()

                )
                .httpBasic()
                .and()
                .headers().frameOptions().disable();
        return http.build();
    }
}

//https://docs.spring.io/spring-security/reference/servlet/configuration/java.html