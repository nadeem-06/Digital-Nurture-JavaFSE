package com.cognizant.mockito;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Exercise 1: Mocking and Stubbing with Mockito
@ExtendWith(MockitoExtension.class)
class Exercise1_MockingTest {

    // @Mock creates a fake UserRepository
    // We don't need a real database!
    @Mock
    UserRepository userRepository;

    // @InjectMocks creates UserService with mock injected
    @InjectMocks
    UserService userService;

    @Test
    void testGetUserName_WhenUserExists() {
        // ARRANGE — stub the mock
        // "When findUserById(1) is called, return 'nadeem'"
        when(userRepository.findUserById(1))
            .thenReturn("nadeem");

        // ACT
        String result = userService.getUserName(1);

        // ASSERT
        // UserService converts to uppercase
        assertEquals("NADEEM", result);
    }

    @Test
    void testGetUserName_WhenUserNotFound() {
        // ARRANGE — stub to return null
        when(userRepository.findUserById(99))
            .thenReturn(null);

        // ACT
        String result = userService.getUserName(99);

        // ASSERT
        assertEquals("User not found", result);
    }

    @Test
    void testRegisterUser_Success() {
        // ARRANGE
        when(userRepository.saveUser("nadeem"))
            .thenReturn(true);

        // ACT
        String result = userService.registerUser("nadeem");

        // ASSERT
        assertEquals("User nadeem registered successfully!", result);
    }

    @Test
    void testRegisterUser_Failure() {
        // ARRANGE — stub to simulate DB failure
        when(userRepository.saveUser("nadeem"))
            .thenReturn(false);

        // ACT
        String result = userService.registerUser("nadeem");

        // ASSERT
        assertEquals("Registration failed!", result);
    }

    @Test
    void testRegisterUser_EmptyUsername() {
        // No stub needed — service validates before calling repo
        String result = userService.registerUser("");
        assertEquals("Invalid username", result);
    }

    @Test
    void testGetTotalUsers() {
        // ARRANGE
        when(userRepository.getUserCount()).thenReturn(42);

        // ACT
        int count = userService.getTotalUsers();

        // ASSERT
        assertEquals(42, count);
    }
}