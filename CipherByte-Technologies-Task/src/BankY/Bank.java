package BankY;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public Account createAccount(String accountHolderName) {
        Account account = new Account(accountHolderName);
        accounts.add(account);
        System.out.println("Account created successfully for " + accountHolderName);
        return account;
    }

    public void saveAccountsToFile(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(accounts);
            System.out.println("Accounts saved to file successfully.");
        }
    }

    public void loadAccountsFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            accounts = (List<Account>) ois.readObject();
            System.out.println("Accounts loaded from file successfully.");
        }
    }

    public Account getAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public void listAllAccounts() {
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getAccountId() + ", Name: " + account.getAccountHolderName() + ", Balance: " + account.getBalance());
        }
    }
}
