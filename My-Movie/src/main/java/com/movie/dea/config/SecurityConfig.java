package com.movie.dea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth-> auth.requestMatchers("/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/movies/new", "/movies/*/delete")
                .hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin( form-> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/movies", true)
                        .permitAll()
                )
                .logout(logout-> logout
                        .logoutSuccessUrl("/login?logout"));
        return http.build();
    }
}
