package com.cognizant.service;

public class EmailService implements NotificationService {

    private String emailServer;
    private int port;

    // Default constructor - required by Spring
    public EmailService() {}

    // Getters
    public String getEmailServer() { return emailServer; }
    public int    getPort()        { return port;        }

    // Setters - Spring uses these for setter injection
    public void setEmailServer(String emailServer) {
        this.emailServer = emailServer;
    }
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[EMAIL] Server : " + emailServer);
        System.out.println("[EMAIL] Port   : " + port);
        System.out.println("[EMAIL] Message: " + message);
    }

    @Override
    public String getServiceType() {
        return "Email";
    }
}