package com.example.Perseo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Perseo.security.JwtService;
import com.example.Perseo.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva la protección CSRF para las solicitudes
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**").permitAll() // Permite el acceso sin autenticación a las rutas
                                                                   // bajo /public/**
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Solo accesible por ADMIN
                        .requestMatchers("/user/**").hasRole("USER") // Solo accesible por USER
                        .anyRequest().authenticated() // Requiere autenticación para todas las demás rutas
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Añade el
                                                                                                         // filtro JWT
                                                                                                         // antes del
                                                                                                         // filtro de
                                                                                                         // autenticación
                                                                                                         // estándar

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de contraseñas para encriptar contraseñas de usuario
    }
}
