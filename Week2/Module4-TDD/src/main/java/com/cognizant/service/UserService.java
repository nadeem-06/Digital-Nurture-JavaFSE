package com.cognizant.service;

public class UserService {

    private UserRepository userRepository;

    // Dependency injected — easy to mock in tests
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(int id) {
        String user = userRepository.findUserById(id);
        if (user == null) {
            return "User not found";
        }
        return user.toUpperCase(); // service adds logic
    }

    public String registerUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return "Invalid username";
        }
        boolean saved = userRepository.saveUser(username);
        if (saved) {
            return "User " + username + " registered successfully!";
        }
        return "Registration failed!";
    }

    public int getTotalUsers() {
        return userRepository.getUserCount();
    }

    public void removeUser(int id) {
        userRepository.deleteUser(id);
    }
}