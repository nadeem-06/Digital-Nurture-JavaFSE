package com.cognizant.service;

public class SMSService implements NotificationService {

    private String provider;

    // Default constructor - required by Spring
    public SMSService() {}

    // Constructor - Spring uses this for constructor injection
    public SMSService(String provider) {
        this.provider = provider;
    }

    // Getter
    public String getProvider() { return provider; }

    // Setter
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS] Provider: " + provider);
        System.out.println("[SMS] Message : " + message);
    }

    @Override
    public String getServiceType() {
        return "SMS";
    }
}