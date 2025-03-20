package org.hca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/signup", "/api/auth/login").permitAll()  // Allow public access
            .anyRequest().authenticated() // Other endpoints require authentication
        )
        .formLogin(login -> login.disable()) // Ensure login form is not interfering
        .httpBasic(basic -> basic.disable()); // Disable basic authentication

    return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Secure password storage
    }
}
