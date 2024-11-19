package com.leafaries.financemanagerbackend.config;

import com.leafaries.financemanagerbackend.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up Spring Security.
 * It includes configuration for the user details service, password encoder,
 * JWT request filter, and security filter chain.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Constructs a new {@code SecurityConfig} with the specified user details service and JWT request filter.
     *
     * @param userDetailsService the service for loading user details
     * @param jwtRequestFilter the filter for processing JWT authentication tokens
     */
    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Provides a password encoder bean to be used for encoding and verifying passwords.
     *
     * @return a {@link PasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an authentication manager bean to be used for authentication operations.
     *
     * @param authenticationConfiguration the configuration for authentication
     * @return an {@link AuthenticationManager} instance
     * @throws Exception if an error occurs while creating the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures the security filter chain, setting up the HTTP security configuration.
     *
     * @param http the {@link HttpSecurity} object for configuring security settings
     * @return a {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs while configuring the filter chain
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session managment
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/register", "/api/login").permitAll() // Public access for authentication
                        .anyRequest().authenticated()); // Require authentication for other requests

        // Add the JWT filter before the default authentication filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configures the authentication manager builder with the user details service and password encoder.
     *
     * @param auth the {@link AuthenticationManagerBuilder} for building the authentication manager
     * @throws Exception if an error occurs while configuring the authentication manager builder
     */
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
