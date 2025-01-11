#include <iostream>
using namespace std;

class intArray {
    int size;
    int index;
    int* arr;

public:
    intArray(int c) : size(c), index(-1) {
        arr = new int[size];
    }

    intArray(const intArray &other) : size(other.size), index(other.index) {
        arr = new int[size];
        for(int i=0; i<=index; i++) {
            arr[i] = other.arr[i];
        }
    }

    ~intArray() {
        delete[] arr;
    }

    int getsize() const {
        return size;
    }

    void insert(const int data) {
        // if(index >= size - 1) {
        //     throw out_of_range("intArray size limit exceed.");
        // }
        arr[++index] = data;
    }

    int &operator[](int i) {
        if (i < 0 || i >= size) {
            throw out_of_range("Index out of bounds");
        }
        return arr[i];
    }


    intArray operator+(const intArray& other) const {
        if (index != other.index) {
            throw invalid_argument("intArray sizes must be equal for addition.");
        }

        intArray result(size);
        for (int i = 0; i <= index; i++) {
            result.arr[i] = arr[i] + other.arr[i];
        }

        return result;
    }

    intArray operator-(const intArray& other) const {
        if (index != other.index) {
            throw invalid_argument("intArray sizes must be equal for subtraction.");
        }

        intArray result(size);
        // result.index = index;
        for (int i = 0; i < size; i++) {
            result.arr[i] = arr[i] - other.arr[i];
        }

        return result;
    }

    void print() const {
        for (int i = 0; i <= index; i++) {
            cout << arr[i] << " ";
        }
        cout << endl;
    }

    void reverse() const {
        for(int i=0; i<=index/2; i++) {
            int temp = arr[i];
            arr[i] = arr[index - i];
            arr[index - i] = temp;
        }
    }

    void sort() const {
        for(int i=0; i<index; i++) {
            for(int j=i+1; j<=index; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
};

int main() {
    intArray a1(3);
    a1.insert(1);
    a1.insert(3);
    a1.insert(2);

    intArray a2(a1);

    a1.print();
    a2.print();

    a2.reverse();
    a2.print();
    
    a2.sort();
    a2.print();

    intArray a3 = a1 + a2;
    a3.print();
}