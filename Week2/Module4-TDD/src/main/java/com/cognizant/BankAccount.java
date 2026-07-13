package com.cognizant;

public class BankAccount {

    private String accountId;
    private String owner;
    private double balance;

    public BankAccount(String accountId, String owner,
                       double initialBalance) {
        this.accountId = accountId;
        this.owner     = owner;
        this.balance   = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be positive!");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal amount must be positive!");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance!");
        }
        balance -= amount;
    }

    public double getBalance()  { return balance;    }
    public String getAccountId(){ return accountId;  }
    public String getOwner()    { return owner;      }
}