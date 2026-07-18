package com.cognizant.service;

import com.cognizant.bean.Employee;

public class EmployeeService {

    // Dependencies - injected by Spring
    private NotificationService notificationService;
    private Employee employee;

    // Default constructor
    public EmployeeService() {}

    // Constructor injection
    public EmployeeService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Setters - for setter injection
    public void setNotificationService(
            NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Business methods
    public void onboardEmployee() {
        System.out.println("----------------------------");
        System.out.println("Onboarding Employee: " +
                           employee.getName());
        System.out.println("Department: " +
                           employee.getDepartment().getDeptName());
        notificationService.sendNotification(
            "Welcome " + employee.getName() +
            "! Your onboarding is complete."
        );
        System.out.println("----------------------------");
    }

    public void notifyEmployee(String message) {
        System.out.println("Notifying via: " +
                           notificationService.getServiceType());
        notificationService.sendNotification(message);
    }
}