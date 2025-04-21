package com.beelac.medstorebackend.config;

import com.beelac.medstorebackend.model.User;
import com.beelac.medstorebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        System.out.println("Custom auth: attempting login for " + email);
        System.out.println("Password entered: " + rawPassword);

        User user = userService.getUserByEmail(email);

        if (user == null) {
            System.out.println("User not found");
            throw new BadCredentialsException("User not found");
        }

        System.out.println("Password in DB: " + user.getPassword());

        if (!user.getPassword().equals(rawPassword)) {
            System.out.println("Password mismatch");
            throw new BadCredentialsException("Invalid password");
        }

        String role = user.isAdmin() ? "ROLE_ADMIN" : "ROLE_CUSTOMER";
        System.out.println("✅ Authenticated: " + email + " | Role: " + role);

        return new UsernamePasswordAuthenticationToken(
            email,
            rawPassword,
            Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
