package com.cognizant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support
    .ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise4_MavenProject {

    private static final Logger logger =
        LoggerFactory.getLogger(Exercise4_MavenProject.class);

    public static void main(String[] args) {

        System.out.println("=== Exercise 4: Maven Project Setup ===\n");

        logger.info("Starting Spring application with Maven...");

        // Demonstrate Maven dependency management
        System.out.println("Maven Dependencies loaded:");
        System.out.println("  - spring-core:6.0.11");
        System.out.println("  - spring-context:6.0.11");
        System.out.println("  - spring-beans:6.0.11");
        System.out.println("  - slf4j-api:2.0.7");
        System.out.println();

        // Start Spring context
        logger.info("Loading Spring IoC container...");
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        logger.info("Spring beans available in context:");
        String[] beanNames = context.getBeanDefinitionNames();
        for (String name : beanNames) {
            logger.info("  Bean: {}", name);
        }

        logger.info("Total beans: {}", beanNames.length);

        ((ClassPathXmlApplicationContext) context).close();
        logger.info("Application shutdown complete.");
    }
}