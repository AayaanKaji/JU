#include <iostream>
using namespace std;

long factorial(const int n) {
	if(n == 0 || n == 1) {
		return 1;
	}
	return n * factorial(n-1);
}
int main() {
	int n;
	cout << "Enter a positive number: " << endl;
	cin >> n;

	if(n < 0) {
		cout << "Don't enter a negative number." << endl;
	}
	else {
		long fac = factorial(n);
		cout << n << "! = " << fac << endl;
	}

	return 0;
}