/*
    1. class "BankAccount" with instance variables:
        AccountNumber (an integer)
        balance (a floating-point number)
        ownerName (a String)
    proper constructor
    with methods:
        balance
        add (to deposit an amount)
        subtract (to withdraw an amount)

    2. class "AccountManager" 
        that contains an array of BankAccounts.
    Write methods:
        create (to create an account)
        delete(to terminate an account)
        deposit (to deposit an amount to an account)
        withdraw (to withdraw an amount from an account)

    Also write a class "Bank”
        add main() function that creates an AccountManager and add 5 accounts
        now print the details of all accounts in this Bank
*/

package q4;

import java.util.HashSet;
import java.util.Set;

class BankAccount {
    private int accountNumber;
    private static Set<Integer> listOfAllAccountNumber = new HashSet<>(); 
    private double balance;
    private String ownerName;

    public BankAccount(int accountNumber, double initialBalance, String ownerName) {
        if (listOfAllAccountNumber == null) {
            System.out.println("Error: code 1");
            return;
        }
    
        if (listOfAllAccountNumber.contains(accountNumber)) {
            System.out.println("Error: Account Number already exists.");
            return;
        }
    
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.ownerName = ownerName;
    
        listOfAllAccountNumber.add(accountNumber);
    }
    

    public double getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void add(double amount) {
        balance += amount;
        System.out.println("Deposited $" + amount + " into account " + accountNumber);
    }

    public void subtract(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + " from account " + accountNumber);
        } else {
            System.out.println("Insufficient funds in account " + accountNumber);
        }
    }

    public void printDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Balance: $" + balance);
    }
}

class AccountManager {
    private BankAccount[] accounts;
    private int accountIndex;

    public AccountManager(int maxAccounts) {
        accounts = new BankAccount[maxAccounts];
        accountIndex = 0;
    }

    public void create(int accountNumber, double initialBalance, String ownerName) {
        if (accountIndex < accounts.length) {
            BankAccount newAcc = new BankAccount(accountNumber, initialBalance, ownerName);
            if(newAcc.getAccountNumber() == 0)
                return;
            accounts[accountIndex++] = newAcc;
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Cannot create more accounts. Limit reached.");
        }
    }

    public void delete(int accountNumber) {
        for (int i = 0; i < accountIndex; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                System.arraycopy(accounts, i + 1, accounts, i, accountIndex - i - 1);
                accounts[--accountIndex] = null;
                System.out.println("Account deleted successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void deposit(int accountNumber, double amount) {
        for (BankAccount account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                account.add(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdraw(int accountNumber, double amount) {
        for (BankAccount account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                account.subtract(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void printAllAccounts() {
        for (BankAccount account : accounts) {
            if (account != null) {
                account.printDetails();
                System.out.println();
            }
        }
    }
}

public class Bank {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager(10);

        accountManager.create(101, 1000.0, "John Doe");
        accountManager.create(102, 2000.0, "Jane Smith");
        accountManager.create(103, 1500.0, "Bob Johnson");
        accountManager.create(104, 3000.0, "Alice Brown");
        accountManager.create(105, 2500.0, "Charlie Davis");
        // accountManager.create(105, 2500.0, "Charlie Davis");

        accountManager.delete(101);
        accountManager.deposit(101, 100);
        accountManager.deposit(102, 100);
        accountManager.withdraw(105, 2000);
        accountManager.withdraw(105, 2000);

        System.out.println("Details of all accounts in the bank:");
        accountManager.printAllAccounts();
    }
}

