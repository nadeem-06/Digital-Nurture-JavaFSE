package com.cognizant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 1: Setting up JUnit 5
// Proves JUnit is configured correctly
class Exercise1_JUnitSetupTest {

    @Test
    void testJUnitIsWorking() {
        // Most basic test — just proves setup works
        System.out.println("JUnit 5 is working!");
        assertTrue(true, "JUnit setup successful");
    }

    @Test
    void testSimpleAddition() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    void testStringNotNull() {
        String name = "Nadeem";
        assertNotNull(name, "Name should not be null");
    }
}