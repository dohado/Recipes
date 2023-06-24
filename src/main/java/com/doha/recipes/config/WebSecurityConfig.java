package com.doha.recipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui.html/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic().and().formLogin();
        return http.build();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
