package com.seconddeal.backend.security;
//import com.seconddeal.backend.security.JwtFilter
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.swing.*;

@Configuration  //  Tells Spring:  This class contains configuration settings.  Spring loads it when the application starts.

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // disable isliye kyukki jwt tokens validation karta hai
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/auth/**").permitAll() // login and register is public
                        .requestMatchers("/api/products/all").permitAll() // anyone can browse
                        .requestMatchers("/api/products/search").permitAll()
                        .requestMatchers("/api/products/{id}").permitAll()
                        .requestMatchers("/api/images/**").permitAll()
                        .anyRequest().authenticated()  // baki ki request authenticat honi chiye we cannot permit all other requests
        )
         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
