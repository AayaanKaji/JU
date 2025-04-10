#include <iostream>
using namespace std;

int main() {
	cout << "Temperature Conversion Table(C to F)" << endl;
	
	cout << "Celsius(C)" << "    " << "Fahrenheit(F)" << endl;

	for(float c=0; c<=100; c++) {
		float f = (9*c)/5 + 32;
		cout << "   " << c << "              " << f << endl;
	}

	return 0;
}