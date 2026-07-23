package com.cognizant.model;

public class AuthResponse {

    private String token;
    private String username;
    private String message;

    // Default constructor
    public AuthResponse() {}

    public AuthResponse(String token,
                        String username,
                        String message) {
        this.token    = token;
        this.username = username;
        this.message  = message;
    }

    // Getters
    public String getToken()    { return token;    }
    public String getUsername() { return username; }
    public String getMessage()  { return message;  }

    // Setters
    public void setToken(String token)       { this.token    = token;    }
    public void setUsername(String username) { this.username = username; }
    public void setMessage(String message)   { this.message  = message;  }
}