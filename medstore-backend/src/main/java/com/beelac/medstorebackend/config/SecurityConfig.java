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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final CustomAuthenticationProvider customAuthProvider;

	public SecurityConfig(CustomAuthenticationProvider customAuthProvider) {
		this.customAuthProvider = customAuthProvider;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(customAuthProvider);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	    	.cors(Customizer.withDefaults())
	        .authenticationProvider(customAuthProvider)
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	            .requestMatchers("/auth/login").permitAll()
	            .requestMatchers("/users/**").permitAll()
	            .requestMatchers("/products/**").permitAll()
	            .requestMatchers("/categories/**").permitAll()
	            .requestMatchers("/cart/**", "/cart-details/**").permitAll()
	            .requestMatchers("/orders/**", "/order-details/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> session
	        	    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	        	)
	        .formLogin(Customizer.withDefaults())
	        .logout(logout -> logout.permitAll());

	    return http.build();
	}
}
