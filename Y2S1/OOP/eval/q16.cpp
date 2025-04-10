#include <iostream>
using namespace std;

void print(int* arr, int n) {
	for(int i=0; i<n; i++) {
		cout << *(arr+i) << " ";
	}
	cout << endl;
}

void print(int* arr, int row, int col) {\
	for(int i=0; i<row; i++) {
		for(int j=0; j<col; j++) {
			cout << *(arr+((i*col)+j)) << " ";
		}
		cout << endl;
	}
	cout << endl;
}

int main () {
	int one_d[5] = {1, 2, 3, 4, 5};
	int size = 5;
	int two_d[3][3] = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
                      };
	int row = 3, col = 3;

	cout << "1D array is: " << endl;
	print(&one_d[0], size);
	cout << "2D array is: " << endl;	
	print(&two_d[0][0], row, col);

	return 0;
}