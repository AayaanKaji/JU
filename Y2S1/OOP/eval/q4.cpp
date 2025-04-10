#include<iostream>
using namespace std;

int main() {
	cout << "Prints 1 to 10" << endl;
	
	for(int i=1; i<=10; i++) {
		cout << i << "  ";
	}

	// cout << "Attempting to access 'i' outside the loop: " << i << endl;

	return 0;
}