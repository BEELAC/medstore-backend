package com.beelac.medstorebackend.services.impl;

import com.beelac.medstorebackend.model.User;
import com.beelac.medstorebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting login lookup for: " + email);

        User user = userService.getUserByEmail(email);

        if (user == null) {
            System.out.println("User not found: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        String role = user.isAdmin() ? "ROLE_ADMIN" : "ROLE_CUSTOMER";
        System.out.println("User found: " + user.getEmail() + " | Role: " + role);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), // this should be the hashed one
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}
