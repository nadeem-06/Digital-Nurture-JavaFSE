package com.cognizant;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Exercise 4: AAA Pattern + @BeforeEach @AfterEach
class Exercise4_AAAPatternTest {

    // Test fixture — shared object for all tests
    BankAccount account;

    // Runs BEFORE each test — fresh setup
    @BeforeEach
    void setUp() {
        System.out.println("--- Setting up test ---");
        account = new BankAccount("ACC001", "Nadeem", 10000.00);
    }

    // Runs AFTER each test — cleanup
    @AfterEach
    void tearDown() {
        System.out.println("--- Test complete. Balance: "
                           + account.getBalance() + " ---\n");
        account = null; // cleanup
    }

    // Runs ONCE before all tests in class
    @BeforeAll
    static void setUpAll() {
        System.out.println("=== Starting BankAccount Tests ===\n");
    }

    // Runs ONCE after all tests in class
    @AfterAll
    static void tearDownAll() {
        System.out.println("=== All BankAccount Tests Complete ===");
    }

    @Test
    void testDeposit_AAA() {
        // ARRANGE — set up data
        double depositAmount = 5000.00;
        double expectedBalance = 15000.00;

        // ACT — perform the action
        account.deposit(depositAmount);

        // ASSERT — verify the result
        assertEquals(expectedBalance, account.getBalance(),
            "Balance should be 15000 after deposit");
    }

    @Test
    void testWithdrawal_AAA() {
        // ARRANGE
        double withdrawAmount = 3000.00;
        double expectedBalance = 7000.00;

        // ACT
        account.withdraw(withdrawAmount);

        // ASSERT
        assertEquals(expectedBalance, account.getBalance(),
            "Balance should be 7000 after withdrawal");
    }

    @Test
    void testOverdraftThrowsException_AAA() {
        // ARRANGE
        double overdraftAmount = 50000.00;

        // ACT + ASSERT
        assertThrows(
            IllegalStateException.class,
            () -> account.withdraw(overdraftAmount),
            "Should throw exception for insufficient balance"
        );
    }

    @Test
    void testNegativeDepositThrowsException_AAA() {
        // ARRANGE
        double negativeAmount = -500.00;

        // ACT + ASSERT
        assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(negativeAmount),
            "Should throw exception for negative deposit"
        );
    }

    @Test
    void testAccountDetails_AAA() {
        // ARRANGE — already done in setUp()

        // ACT — just read values

        // ASSERT
        assertAll("Account details",
            () -> assertEquals("ACC001", account.getAccountId()),
            () -> assertEquals("Nadeem", account.getOwner()),
            () -> assertEquals(10000.00, account.getBalance())
        );
    }
}