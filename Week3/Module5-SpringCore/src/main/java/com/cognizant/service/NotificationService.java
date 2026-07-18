package com.cognizant.service;

public interface NotificationService {

    void sendNotification(String message);

    String getServiceType();
}