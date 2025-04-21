package com.beelac.medstorebackend.controllers;

import com.beelac.medstorebackend.model.LoginRequest;
import com.beelac.medstorebackend.model.User;
import com.beelac.medstorebackend.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    // constructor injection to ensure both are wired
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);

            // ✅ store in security context
            SecurityContextHolder.getContext().setAuthentication(auth);

            // ✅ force Spring to bind context to session
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            User user = userService.getUserByEmail(request.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("userId", user.getId());
            response.put("isAdmin", user.isAdmin());

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
