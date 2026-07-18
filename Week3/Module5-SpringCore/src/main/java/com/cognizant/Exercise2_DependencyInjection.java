package com.cognizant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.service
        .EmployeeService;

public class Exercise2_DependencyInjection {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("Exercise 2: Dependency Injection");
        System.out.println("================================\n");

        // Load Spring IoC container
        ApplicationContext context =
            new ClassPathXmlApplicationContext(
                "applicationContext.xml"
            );

        // ─────────────────────────────────────
        // Test 1: Setter Injection with Email
        // ─────────────────────────────────────
        System.out.println("--- Setter Injection (Email) ---");
        EmployeeService emailService =
            context.getBean(
                "employeeServiceEmail",
                EmployeeService.class
            );
        emailService.onboardEmployee();

        // ─────────────────────────────────────
        // Test 2: Constructor Injection with SMS
        // ─────────────────────────────────────
        System.out.println("--- Constructor Injection (SMS) ---");
        EmployeeService smsService =
            context.getBean(
                "employeeServiceSMS",
                EmployeeService.class
            );
        smsService.onboardEmployee();

        // ─────────────────────────────────────
        // Test 3: Notify via Email
        // ─────────────────────────────────────
        System.out.println("--- Notify via Email ---");
        emailService.notifyEmployee(
            "Your salary has been credited!"
        );

        System.out.println();

        // ─────────────────────────────────────
        // Test 4: Notify via SMS
        // ─────────────────────────────────────
        System.out.println("--- Notify via SMS ---");
        smsService.notifyEmployee(
            "Your salary has been credited!"
        );

        // Close context
        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring context closed.");
        System.out.println("================================");
    }
}