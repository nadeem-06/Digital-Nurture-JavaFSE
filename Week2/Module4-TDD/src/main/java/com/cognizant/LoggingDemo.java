package com.cognizant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 1: SLF4J Logging - all levels
public class LoggingDemo {

    // Create logger for this class
    private static final Logger logger =
        LoggerFactory.getLogger(LoggingDemo.class);

    public static void main(String[] args) {

        System.out.println("=== SLF4J Logging Demo ===\n");

        // TRACE — most detailed, lowest level
        logger.trace("TRACE: Application started in trace mode");

        // DEBUG — for developers
        logger.debug("DEBUG: Loading configuration files...");

        // INFO — general information (most common)
        logger.info("INFO: Application started successfully");
        logger.info("INFO: Server running on port {}", 8080);

        // WARN — something unexpected but not breaking
        logger.warn("WARN: Memory usage is above 80%");
        logger.warn("WARN: User {} attempted {} login failures",
                    "nadeem", 3);

        // ERROR — something went wrong
        logger.error("ERROR: Database connection failed!");

        // Parameterized logging (efficient — no string concat)
        String username = "Nadeem";
        int userId = 101;
        logger.info("User logged in: name={}, id={}", username, userId);

        // Logging exceptions
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("ERROR: Calculation failed - {}", e.getMessage());
            logger.error("Full stack trace:", e);
        }

        logger.info("INFO: Application shutting down gracefully");
    }
}