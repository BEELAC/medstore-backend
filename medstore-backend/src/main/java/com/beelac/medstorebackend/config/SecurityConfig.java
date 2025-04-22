package com.beelac.medstorebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthProvider) {
        return new ProviderManager(customAuthProvider);
    }
    
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CustomAuthenticationProvider customAuthProvider,
                                                   CustomAuthenticationEntryPoint customEntryPoint) throws Exception {
        http
        	.securityContext(context -> context
        			.securityContextRepository(securityContextRepository()))
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .authenticationProvider(customAuthProvider)
            .exceptionHandling(e -> e.authenticationEntryPoint(customEntryPoint))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/products/**").permitAll()
                .requestMatchers("/categories/**").permitAll()
                .requestMatchers("/cart/**", "/cart-details/**").permitAll()
                .requestMatchers("/orders/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable())
            .sessionManagement(session -> session
            		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .httpBasic(Customizer.withDefaults())
            .logout(logout -> logout.disable());

        return http.build();
    }
}
