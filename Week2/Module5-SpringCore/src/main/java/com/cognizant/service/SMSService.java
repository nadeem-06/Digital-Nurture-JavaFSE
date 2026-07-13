package com.cognizant.service;

public class SMSService implements NotificationService {

    private String provider;

    public SMSService() {}
    public SMSService(String provider) { this.provider = provider; }

    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS] Provider: " + provider);
        System.out.println("[SMS] Message: " + message);
    }

    @Override
    public String getServiceType() { return "SMS"; }

    public void setProvider(String p) { this.provider = p; }
}