package BankY;

import java.io.IOException;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        try {
            bank.loadAccountsFromFile("accounts.ser");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous account data found.");
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Bank System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. List All Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    bank.createAccount(name);
                    break;
                case 2:
                    System.out.print("Enter account ID: ");
                    String depositAccountId = scanner.nextLine();
                    Account depositAccount = bank.getAccountById(depositAccountId);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = scanner.nextDouble();
                        depositAccount.deposit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account ID: ");
                    String withdrawAccountId = scanner.nextLine();
                    Account withdrawAccount = bank.getAccountById(withdrawAccountId);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter your account ID: ");
                    String fromAccountId = scanner.nextLine();
                    Account fromAccount = bank.getAccountById(fromAccountId);
                    if (fromAccount != null) {
                        System.out.print("Enter recipient account ID: ");
                        String toAccountId = scanner.nextLine();
                        Account toAccount = bank.getAccountById(toAccountId);
                        if (toAccount != null) {
                            System.out.print("Enter amount to transfer: ");
                            double transferAmount = scanner.nextDouble();
                            fromAccount.transfer(toAccount, transferAmount);
                        } else {
                            System.out.println("Recipient account not found.");
                        }
                    } else {
                        System.out.println("Your account not found.");
                    }
                    break;
                case 5:
                    bank.listAllAccounts();
                    break;
                case 6:
                    running = false;
                    try {
                        bank.saveAccountsToFile("accounts.ser");
                    } catch (IOException e) {
                        System.out.println("Error saving accounts.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
