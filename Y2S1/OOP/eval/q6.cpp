#include <iostream>
using namespace std;

const double PI = 3.14;

double cal_area_circle(double radius) {
	return PI * radius * radius;
}

int main() {
	double r;
	cout << "Enter radius of a circle: " << endl;
	cin >> r;
	if(r < 0) {
		cout << "Radius can't be negative." << endl;
	}
	else {
		double area = cal_area_circle(r);
		cout << "Area of the circle is: " << area << endl;
	}

	return 0;
}