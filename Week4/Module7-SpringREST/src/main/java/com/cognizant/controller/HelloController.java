package com.cognizant.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // ─────────────────────────────────────
    // GET /api/hello
    // Hello World REST endpoint - public
    // ─────────────────────────────────────
    @GetMapping("/api/hello")
    public ResponseEntity<Map<String, String>>
            helloWorld() {

        Map<String, String> response = new HashMap<>();
        response.put("message",
            "Hello World from Spring Boot REST!");
        response.put("module",
            "Module 7 - Spring REST");
        response.put("timestamp",
            LocalDateTime.now().toString());
        response.put("status", "SUCCESS");

        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────
    // GET /api/greet/{name}
    // Greet by name - public
    // ─────────────────────────────────────
    @GetMapping("/api/greet/{name}")
    public ResponseEntity<Map<String, String>>
            greet(@PathVariable String name) {

        Map<String, String> response = new HashMap<>();
        response.put("message",
            "Hello " + name +
            "! Welcome to Spring Boot REST API.");
        response.put("timestamp",
            LocalDateTime.now().toString());

        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────
    // GET /api/info
    // API info endpoint
    // ─────────────────────────────────────
    @GetMapping("/api/info")
    public ResponseEntity<Map<String, Object>>
            getInfo() {

        Map<String, Object> response = new HashMap<>();
        response.put("application",
            "DN 5.0 - Module 7 Spring REST");
        response.put("version", "1.0");
        response.put("endpoints", new String[]{
            "GET  /api/hello",
            "GET  /api/greet/{name}",
            "POST /api/auth/login",
            "GET  /api/countries",
            "GET  /api/countries/{id}",
            "GET  /api/countries/code/{code}",
            "POST /api/countries",
            "PUT  /api/countries/{id}",
            "DELETE /api/countries/{id}"
        });

        return ResponseEntity.ok(response);
    }
}