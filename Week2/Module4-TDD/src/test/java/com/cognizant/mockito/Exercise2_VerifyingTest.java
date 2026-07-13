package com.cognizant.mockito;

import com.cognizant.service.UserRepository;
import com.cognizant.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Exercise 2: Verifying interactions
// Not just checking return value — checking HOW methods were called
@ExtendWith(MockitoExtension.class)
class Exercise2_VerifyingTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testFindUserWasCalledOnce() {
        // ARRANGE
        when(userRepository.findUserById(1)).thenReturn("Nadeem");

        // ACT
        userService.getUserName(1);

        // ASSERT — verify findUserById was called exactly once with id=1
        verify(userRepository, times(1)).findUserById(1);
    }

    @Test
    void testSaveUserWasCalledOnce() {
        // ARRANGE
        when(userRepository.saveUser("Alice")).thenReturn(true);

        // ACT
        userService.registerUser("Alice");

        // ASSERT — verify saveUser was called exactly once
        verify(userRepository, times(1)).saveUser("Alice");
    }

    @Test
    void testSaveUserNeverCalledForEmptyUsername() {
        // ACT
        userService.registerUser(""); // invalid — service rejects early

        // ASSERT — saveUser should NEVER be called for empty username
        verify(userRepository, never()).saveUser(anyString());
    }

    @Test
    void testDeleteUserWasCalled() {
        // ACT
        userService.removeUser(5);

        // ASSERT — deleteUser was called with id=5
        verify(userRepository, times(1)).deleteUser(5);
    }

    @Test
    void testNoMoreInteractionsAfterGetUser() {
        // ARRANGE
        when(userRepository.findUserById(1)).thenReturn("Bob");

        // ACT
        userService.getUserName(1);

        // ASSERT — only findUserById was called, nothing else
        verify(userRepository).findUserById(1);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testArgumentCaptor() {
        // ArgumentCaptor captures what argument was passed to the mock
        ArgumentCaptor<String> captor =
            ArgumentCaptor.forClass(String.class);

        when(userRepository.saveUser(any())).thenReturn(true);

        // ACT
        userService.registerUser("Nadeem");

        // Capture the argument that was passed to saveUser
        verify(userRepository).saveUser(captor.capture());

        // Assert captured value
        assertEquals("Nadeem", captor.getValue());
    }
}