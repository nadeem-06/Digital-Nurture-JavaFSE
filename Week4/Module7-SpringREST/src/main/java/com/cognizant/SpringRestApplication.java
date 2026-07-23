package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            SpringRestApplication.class, args
        );
        System.out.println("\n================================");
        System.out.println("Module 7 - Spring REST API");
        System.out.println("Server started on port 8080");
        System.out.println("================================");
        System.out.println("Available endpoints:");
        System.out.println("  GET  http://localhost:8080/api/hello");
        System.out.println("  GET  http://localhost:8080/api/greet/{name}");
        System.out.println("  POST http://localhost:8080/api/auth/login");
        System.out.println("  GET  http://localhost:8080/api/countries");
        System.out.println("  GET  http://localhost:8080/api/countries/{id}");
        System.out.println("  GET  http://localhost:8080/api/countries/code/{code}");
        System.out.println("  POST http://localhost:8080/api/countries");
        System.out.println("  PUT  http://localhost:8080/api/countries/{id}");
        System.out.println("  DELETE http://localhost:8080/api/countries/{id}");
        System.out.println("================================\n");
    }
}