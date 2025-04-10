#include <iostream>

using namespace std;

class IntArray {
    int* i;
    int size;

public:
    IntArray(int size): size(size) {
        i = new int[size];
    }

    ~IntArray() {
        delete[] i;
    }

    int& operator[](int index) {
        if (index < 0 || index >= size) {
            throw out_of_range("Index out of bounds");
        }
        return i[index];
    }

    friend ostream& operator<<(ostream& os, const IntArray& c) {
        os << "[";
        int k;
        for (k = 0; k < c.size - 1; k++) {
            os << c.i[k];
            os << ", ";
        }
        os << c.i[k] << "]";
        return os;
    }
};

int main() {
    IntArray i(10);
    
    for(int k = 0; k < 10; k++)
        i[k] = k;
    cout << i;
    
    return 0;
}