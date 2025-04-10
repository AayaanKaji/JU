#include <iostream>
using namespace std;

int main() { 
	int x, y;
	y = 1;
	cout << "Prints a Factorial Table" << endl;

	cout << "Enter a positive integer: ";
	cin >> x;

	while (x <= 0) {
		cout << "Invalid Input!\n" << "Enter a positive integer: ";
	    cin >> x;
	}

	for(int i=1; i<=x; i++) {
		y *= i;
		cout << i << "! = " << y << endl; 
	}

	return 0;
} 