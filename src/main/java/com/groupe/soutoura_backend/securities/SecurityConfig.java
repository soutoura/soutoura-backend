package com.groupe.soutoura_backend.securities;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CorsConfigurationSource corsConfigurationSource

    ) throws Exception {
        http .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/auth/**", "api/utilisateurs/register").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/association/**").hasAnyRole("ASSOCIATION", "ADMIN")
                                .requestMatchers("/api/parrain/**").hasAnyRole("PARRAIN", "ADMIN")
                                .requestMatchers("/api/parent/**").hasAnyRole("PARENT", "ADMIN")
                                .requestMatchers("/api/notifications/**").hasAnyRole("PARENT", "ASSOCIATION"
                                ,"PARRAIN", "ADMIN")
                                .requestMatchers("/api/parrainages/**").hasAnyRole("PARRAIN",
                                        "PARENT", "ADMIN")
                                .requestMatchers("/api/enfants/**").hasAnyRole("PARRAIN",
                                        "PARENT", "ASSOCIATION", "ADMIN")
                                .requestMatchers("/api/autorisations/**").hasAnyRole("PARENT",
                                        "PARRAIN")
                                .requestMatchers("/api/depenses/**").hasAnyRole("PARENT", "ASSOCIATION",
                                        "ADMIN", "PARRAIN")
                                .requestMatchers("/api/paiements/**").hasAnyRole("PARENT",
                                        "ASSOCIATION", "ADMIN", "PARRAIN")
                                .requestMatchers("/api/upload/**").hasAnyRole("PARENT", "ASSOCIATION",
                                        "ADMIN", "PARRAIN")
                                .requestMatchers("/api/files/**").hasAnyRole("PARENT", "ASSOCIATION","ADMIN", "PARRAIN")
                                .anyRequest().authenticated()

                ) .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //CorsConfigurationSource?
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(List.of("POST", "GET", "DELETE", "PUT"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8083", "http://localhost:4200"));
        //Autoriser l'envoi de cookie/jwt
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}

