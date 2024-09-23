package BankY;

import java.io.Serializable;
import java.util.UUID;

public class Account implements Serializable {
    private String accountId;
    private String accountHolderName;
    private double balance;

    public Account(String accountHolderName) {
        this.accountId = UUID.randomUUID().toString();
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited successfully. New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully. New Balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void transfer(Account toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            toAccount.balance += amount;
            System.out.println(amount + " transferred successfully from " + this.accountHolderName + " to " + toAccount.accountHolderName);
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }
}
