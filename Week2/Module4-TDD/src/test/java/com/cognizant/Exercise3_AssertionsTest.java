package com.cognizant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 3: All major JUnit 5 assertions
class Exercise3_AssertionsTest {

    Calculator calc = new Calculator();

    // assertEquals
    @Test
    void testAddition() {
        assertEquals(10, calc.add(6, 4),
            "6 + 4 should be 10");
    }

    // assertNotEquals
    @Test
    void testSubtractionNotZero() {
        assertNotEquals(0, calc.subtract(10, 3),
            "10 - 3 should not be 0");
    }

    // assertTrue / assertFalse
    @Test
    void testIsEven() {
        assertTrue(calc.isEven(4),   "4 is even");
        assertFalse(calc.isEven(7),  "7 is not even");
    }

    // assertNull / assertNotNull
    @Test
    void testNullChecks() {
        String value = null;
        assertNull(value, "Value should be null");

        String name = "Cognizant";
        assertNotNull(name, "Name should not be null");
    }

    // assertThrows — test exception is thrown
    @Test
    void testDivideByZeroThrowsException() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> calc.divide(10, 0),
            "Division by zero should throw ArithmeticException"
        );
        assertEquals("Cannot divide by zero!", exception.getMessage());
    }

    // assertThrows for illegal argument
    @Test
    void testFactorialNegativeThrowsException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> calc.factorial(-1),
            "Negative factorial should throw IllegalArgumentException"
        );
    }

    // assertAll — run multiple assertions together
    @Test
    void testMultipleCalculations() {
        assertAll("Calculator operations",
            () -> assertEquals(15, calc.add(10, 5)),
            () -> assertEquals(5,  calc.subtract(10, 5)),
            () -> assertEquals(50, calc.multiply(10, 5)),
            () -> assertEquals(2.0, calc.divide(10, 5))
        );
    }

    // assertEquals with delta for doubles
    @Test
    void testDivisionResult() {
        assertEquals(3.333, calc.divide(10, 3), 0.001,
            "10/3 should be approx 3.333");
    }
}