/*
    class "Account" containing
        data members 'accountNumber', 'holderName', 'balance'
        constructors
        necessary accessor/modifier
    
    create two class "SavingsAccount" and "CurrentAccount" extending from this class
    Savings Account will have
        member variable 'interestRate'
        member function 'calculateYearlyInterest'
    
    Write another class "Manager" that contains a list Account
    
    a main() function to create an instance of Manager class
        Add two SavingsAccount and three CurrentAccount to Manager
        Calculate interest of each SavingsAccount
        Print the details of all accounts
*/
package q8;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

class Account {
    private int accountNumber;
    private String holderName;
    private double balance;
    private static Set<Integer> listOfAccountNumber = new HashSet<>();

    public static boolean checkIfAccountExists(int accountNumber) {
        if(listOfAccountNumber.contains(accountNumber)) {
            System.out.println("Error: Acoount number already exists!");
            return true;
        }
        return false;
    }

    public Account(int accountNumber, String holderName, double balance) {
        if(checkIfAccountExists(accountNumber)) {
            return;
        }
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        listOfAccountNumber.add(accountNumber);
        System.out.println("Account created successfully.");
    } 

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateYearlyInterest() {
        return getBalance() * interestRate;
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(int accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }
}

class Manager {
    private List<Account> accounts;

    public Manager() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void printInterest() {
        System.out.println("Interest for each Savings Accounts:- ");
        for(Account account : accounts) {
            if(account instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                double yearlyInterest = savingsAccount.calculateYearlyInterest();
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Interest Rate: " + savingsAccount.getInterestRate() * 100 + "%");
                System.out.println("Yearly Interest: $" + yearlyInterest);
                System.out.println();
            }
        }
    }

    public void printAccountDetails() {
        System.out.println("Details of all accounts:");
        for(Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Holder Name: " + account.getHolderName());
            System.out.println("Balance: $" + account.getBalance());
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        SavingsAccount sa1 = new SavingsAccount(101, "John Doe", 5000.0, 5.1);
        SavingsAccount sa2 = new SavingsAccount(102, "Jane Smith", 8000.0, 3.0);

        CurrentAccount ca1 = new CurrentAccount(201, "Bob Johnson", 10000.0);
        CurrentAccount ca2 = new CurrentAccount(202, "Alice Brown", 12000.0);
        CurrentAccount ca3 = new CurrentAccount(203, "Charlie Davis", 15000.0);

        manager.addAccount(sa1);
        manager.addAccount(sa2);
        manager.addAccount(ca1);
        manager.addAccount(ca2);
        manager.addAccount(ca3);

        manager.printInterest();
        manager.printAccountDetails();
    }
}
