package com.cognizant.service;

public interface UserRepository {
    String findUserById(int id);
    boolean saveUser(String username);
    void deleteUser(int id);
    int getUserCount();
}