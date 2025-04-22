package com.beelac.medstorebackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "demopass123";
        String hash = encoder.encode(rawPassword);
        System.out.println("Hashed password for '" + rawPassword + "':");
        System.out.println(hash);
    }
}
