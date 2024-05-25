package com.example.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.filter.JwtAuthFilter;
import com.example.jwt.services.UserInfoService;

@Configuration
@EnableWebSecurity // Enable Spring Security
@EnableMethodSecurity // Enable method-level security annotations
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter; // JWT authentication filter

    // Bean for providing UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(); // Return an instance of UserInfoService as the UserDetailsService
    }

    // Configure security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // Disable CSRF protection
                .authorizeHttpRequests()
                .requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll() // Permit access to specific endpoints without authentication
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/user/**").authenticated() // Require authentication for endpoints under /auth/user/**
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/admin/**").authenticated() // Require authentication for endpoints under /auth/admin/**
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Set session creation policy to stateless
                .and()
                .authenticationProvider(authenticationProvider()) // Set custom authentication provider
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT authentication filter before UsernamePasswordAuthenticationFilter
                .build();
    }

    // Bean for PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Return an instance of BCryptPasswordEncoder as the PasswordEncoder
    }

    // Bean for AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); // Create a DaoAuthenticationProvider
        authenticationProvider.setUserDetailsService(userDetailsService()); // Set UserDetailsService
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Set PasswordEncoder
        return authenticationProvider; // Return the configured authentication provider
    }

    // Bean for AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Return the configured AuthenticationManager
    }
}
