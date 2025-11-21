#include <iostream>
using namespace std;

class Vector {
    int size;
    int index;
    int* arr;

public:
    Vector(int c) : size(c), index(0) {
        arr = new int[size];
    }

    Vector(const Vector& other) : size(other.size), index(other.index) {
        arr = new int[size];
        for(int i=0; i<index; i++) {
            arr[i] = other.arr[i];
        }
    }

    ~Vector() {
        delete[] arr;
    }

    int getsize() const {
        return size;
    }

    void insert(const int data) {
        // if(index >= size - 1) {
        //     throw out_of_range("Vector size limit exceed.");
        // }
        arr[index++] = data;
    }

    int &operator[](int i) {
        if (i < 0 || i >= size) {
            throw out_of_range("Index out of bounds");
        }
        return arr[i];
    }


    Vector operator+(const Vector& other) const {
        if (size != other.size) {
            throw invalid_argument("Vector sizes must be equal for addition.");
        }

        Vector result(size);
        for (int i = 0; i < size; i++) {
            result.arr[i] = arr[i] + other.arr[i];
        }

        return result;
    }

    Vector operator-(const Vector& other) const {
        if (size != other.size) {
            throw invalid_argument("Vector sizes must be equal for subtraction.");
        }

        Vector result(size);
        for (int i = 0; i < size; i++) {
            result.arr[i] = arr[i] - other.arr[i];
        }

        return result;
    }

    bool operator==(const Vector& other) const {
        if (size != other.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (arr[i] != other.arr[i]) {
                return false;
            }
        }

        return true;
    }

    bool operator<(const Vector& other) const {
        if (size != other.size) {
            throw invalid_argument("Vector sizes must be equal for comparison.");
        }

        for (int i = 0; i < size; i++) {
            if (arr[i] >= other.arr[i]) {
                return false;
            }
        }

        return true;
    }

    bool operator>(const Vector& other) const {
        if (size != other.size) {
            throw invalid_argument("Vector sizes must be equal for comparison.");
        }

        for (int i = 0; i < size; i++) {
            if (arr[i] <= other.arr[i]) {
                return false;
            }
        }

        return true;
    }

    void print() const {
        for (int i = 0; i < size; i++) {
            cout << arr[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    Vector v1(10), v2(10), v3(11);

    for(int i=0; i<10; i++) {
        v1.insert(i);
        v2.insert(i+1);
        v3.insert(i);
    }
    v3.insert(10);
    // v3.insert(11);
    v3.print();

    Vector v4(v1);

    cout<<v1.getsize()<<" "<<v2.getsize()<<" "<<v3.getsize()<<" "<<v4.getsize()<<endl;
    
    Vector v5 = v1 + v2, v6 = v1 - v2;
    
    v5.print();
    v6.print();
    
    (v1 + v4).print();
    
    cout << (v1 == v4) << " " << (v1 > v2) << endl;
}