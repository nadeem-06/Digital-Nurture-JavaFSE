package com.cognizant.service;

public class EmailService implements NotificationService {

    private String emailServer;
    private int    port;

    public EmailService() {}

    public EmailService(String emailServer, int port) {
        this.emailServer = emailServer;
        this.port        = port;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[EMAIL] Sending via " +
                           emailServer + ":" + port);
        System.out.println("[EMAIL] Message: " + message);
    }

    @Override
    public String getServiceType() { return "Email"; }

    public void setEmailServer(String s) { this.emailServer = s; }
    public void setPort(int p)           { this.port = p;        }
}