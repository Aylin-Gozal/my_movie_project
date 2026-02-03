package com.movie.dea.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class InMemory {
    @Bean
    public UserDetailsService users(){
        // admin
        UserDetails admin = User.builder()
                .username("admin")
                .password("1234")
                .roles("ADMIN")
                .build();

        // user
        UserDetails user = User.builder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
