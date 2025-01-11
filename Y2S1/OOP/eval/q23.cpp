#include <iostream>
#include <random>
#include <string>
using namespace std;

class Account {
    static int nextAccNo;
    const int accountNumber;
    string name;
    double balance;
    const double minBalance = 1000;
    const double interestRate = .043;

public:
    Account(string name, double deposit) : accountNumber(nextAccNo++), name(name), balance(deposit) {
        if(balance < minBalance) {
            cout << "Min balance of 1000 has to be deposited." << endl;
            exit(1);
        }
    }

    Account() : name("Default"), balance(0), accountNumber(0) {}

    string getName() const {
        return name;
    }

    double getBalance() const {
        return balance;
    }

    double withdraw(double const amt) {
        if(balance - amt < minBalance) {
            cout << "Insufficient balance." << endl;
            return -1;
        }

        balance -= amt;
        return amt;
    }

    double deposit(double const amt) {
        if(amt < 0) {
            cout << "Negative deposit not supported." << endl;
            return balance;
        }

        balance += amt;
        return balance;
    }

    double calculateInterest() {
        double interest = balance * interestRate;
        balance += interest;
        return interest;
    }

    void show() const {
        cout << "Account Number: " << accountNumber 
             << "\nAccount Holder Name: " << name 
             << "\nAvailable Balance: " << balance << endl;
    }
};

int Account::nextAccNo = 1;

int main() {
    mt19937 mt(500);
    uniform_int_distribution<int> dist(10, 1000);

    const int num = 5;
    Account* accounts[num];
    for (int i = 0; i < num; i++) {
        string name = "A" + to_string(i + 1);
        double balance = dist(mt) * 100;
        accounts[i] = new Account(name, balance);
    }

    for(int i=0; i<num; i++) {
        accounts[i]->show();
    }

    double totalInterest = 0;
    for(int i=0; i<num; i++) {
        totalInterest += accounts[i]->calculateInterest();
    }

    cout << "\nTotal interest to be paid: " << totalInterest << "\n" << endl;

    for(int i=0; i<num; i++) {
        accounts[i]->show();
    }
}