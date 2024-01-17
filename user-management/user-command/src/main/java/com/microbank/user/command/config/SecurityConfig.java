package com.microbank.user.command.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()).csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers("/h2/**").permitAll() // New Line: allows us to access the h2 console without the need to authenticate. ' ** '  instead of ' * ' because multiple path levels will follow /h2.
            .requestMatchers("/**").permitAll() // temporary rule
            .anyRequest().authenticated());
        return http.build();
    }    

}
