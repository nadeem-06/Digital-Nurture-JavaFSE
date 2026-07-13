package com.cognizant;

import com.cognizant.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support
    .ClassPathXmlApplicationContext;

public class Exercise2_DependencyInjection {

    public static void main(String[] args) {

        System.out.println("=== Exercise 2: Dependency Injection ===\n");

        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        // Test 1: Setter Injection with EmailService
        System.out.println("--- Setter Injection (Email) ---");
        EmployeeService emailEmpService =
            context.getBean("employeeService", EmployeeService.class);
        emailEmpService.onboardEmployee();

        System.out.println();

        // Test 2: Constructor Injection with SMSService
        System.out.println("--- Constructor Injection (SMS) ---");
        EmployeeService smsEmpService =
            context.getBean("employeeServiceSMS", EmployeeService.class);
        smsEmpService.onboardEmployee();

        System.out.println();

        // Test 3: Swapping notification service at runtime
        System.out.println("--- Notify via Email ---");
        emailEmpService.notifyEmployee(
            "Your salary has been processed!"
        );

        System.out.println();

        System.out.println("--- Notify via SMS ---");
        smsEmpService.notifyEmployee(
            "Your salary has been processed!"
        );

        ((ClassPathXmlApplicationContext) context).close();
    }
}