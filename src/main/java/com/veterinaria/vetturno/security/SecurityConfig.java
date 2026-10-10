package com.veterinaria.vetturno.security;

import com.veterinaria.vetturno.model.Rol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers("/api/autenticacion/**").permitAll()
                                .requestMatchers("/api/citas/**").hasAnyRole(Rol.USER.name(),Rol.ADMIN.name())
                                .requestMatchers("/api/propietarios/**").hasAnyRole(Rol.USER.name(),Rol.ADMIN.name())
                                .requestMatchers("/api/mascotas/**").hasAnyRole(Rol.USER.name(),Rol.ADMIN.name())
                                .requestMatchers(HttpMethod.GET,"/api/veterinarios/**").hasAnyRole(Rol.USER.name(),Rol.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/api/veterinarios/**").hasRole(Rol.ADMIN.name())
                                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
