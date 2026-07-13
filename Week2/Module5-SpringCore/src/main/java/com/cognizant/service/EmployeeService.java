package com.cognizant.service;

import com.cognizant.bean.Employee;

public class EmployeeService {

    // Dependencies — injected by Spring
    private NotificationService notificationService;
    private Employee employee;

    public EmployeeService() {}

    // Constructor injection
    public EmployeeService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Setter injection
    public void setNotificationService(NotificationService s) {
        this.notificationService = s;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void onboardEmployee() {
        System.out.println("Onboarding: " + employee.getName());
        notificationService.sendNotification(
            "Welcome " + employee.getName() + "! Your onboarding is complete."
        );
    }

    public void notifyEmployee(String message) {
        System.out.println("Notifying via: " +
                           notificationService.getServiceType());
        notificationService.sendNotification(message);
    }
}