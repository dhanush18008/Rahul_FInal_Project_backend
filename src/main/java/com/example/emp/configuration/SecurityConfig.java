package com.example.emp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("USER")
                        .authorities("read")
                        .build()
        );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors->cors.disable())
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(form->form.disable())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
