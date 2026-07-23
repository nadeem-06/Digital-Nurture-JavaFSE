package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.AuthRequest;
import com.cognizant.model.AuthResponse;
import com.cognizant.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ─────────────────────────────────────
    // POST /api/auth/login
    // Generate JWT token
    // ─────────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequest request) {

        try {
            // Load user from UserDetailsService
            UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                    request.getUsername()
                );

            // Verify password
            if (!passwordEncoder.matches(
                    request.getPassword(),
                    userDetails.getPassword())) {
                throw new BadCredentialsException(
                    "Invalid password!"
                );
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(
                request.getUsername()
            );

            // Return token
            return ResponseEntity.ok(
                new AuthResponse(
                    token,
                    request.getUsername(),
                    "Login successful!"
                )
            );

        } catch (BadCredentialsException ex) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(
                    null,
                    request.getUsername(),
                    "Invalid credentials!"
                ));
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(
                    null,
                    request.getUsername(),
                    "User not found!"
                ));
        }
    }

    // ─────────────────────────────────────
    // POST /api/auth/validate
    // Validate JWT token
    // ─────────────────────────────────────
    @PostMapping("/validate")
    public ResponseEntity<?> validate(
            @RequestParam String token,
            @RequestParam String username) {

        try {
            boolean isValid =
                jwtUtil.validateToken(token, username);

            if (isValid) {
                return ResponseEntity.ok(
                    "Token is valid for user: " + username
                );
            } else {
                return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Token is invalid or expired!");
            }
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Token validation failed: " +
                      ex.getMessage());
        }
    }
}