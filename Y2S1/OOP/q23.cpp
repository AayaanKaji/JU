#include <iostream>
#include <vector>
#include <random>
#include <string>
using namespace std;

class Account {
    static int nextAccNo;
    int accountNumber;
    mutable string name;
    mutable double balance;
    const double minBalance = 1000;
    const double interestRate = .043;

public:
    Account(string name, double balance) : accountNumber(nextAccNo++), name(name) {
        if(balance < minBalance) {
            cout << "Min balance of 1000 has to be deposited." << endl;
            exit(1);
        }
        this->balance = balance;
    }

    string getName() const {
        return name;
    }

    double getBalance() const {
        return balance;
    }

    double withdraw(double const amt) const {
        if(balance - amt < minBalance) {
            cout << "insufficient balance." << endl;
            return -1;
        }

        double wd = balance - amt;
        return wd;
    }

    double deposit(double const amt) const {
        if(amt < 0) {
            cout << "Negative deposit not supported." << endl;
            return balance;
        }

        balance += amt;
        return balance;
    }

    double calculateInterest() const {
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

    vector<Account> accounts;
    for (int i = 0; i < 5; i++) {
        string name = "A" + to_string(i + 1);
        double balance = dist(mt) * 100;
        Account account(name, balance);
        accounts.push_back(account);
    }

    for(const Account& account : accounts) {
        account.show();
    }

    double totalInterest = 0;
    for(Account& account : accounts) {
        totalInterest += account.calculateInterest();
    }

    cout << "\nTotal interest to be paid: " << totalInterest << "\n" << endl;

    for(const Account& account : accounts) {
        account.show();
    }
}
