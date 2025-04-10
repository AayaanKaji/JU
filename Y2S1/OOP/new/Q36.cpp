#include <iostream>
using namespace std;

class Table {
    int rows;
    int cols;
    int** data;

public:
    Table(int r, int c) : rows(r), cols(c) {
        data = new int*[rows];
        for (int i = 0; i < rows; i++) {
            data[i] = new int[cols];
        }
    }

    ~Table() {
        for (int i = 0; i < rows; ++i) {
            delete[] data[i];
        }
        delete[] data;
    }

    friend istream& operator>>(istream& is, Table& other) {
        cout << "Enter elements for Table T of size, Row: " << other.rows << " , Column: " << other.cols << endl;

        for (int i = 0; i < other.rows; i++) {
            cout << "Enter " << other.cols << " elements for row " << (i + 1) << ": ";
            for (int j = 0; j < other.cols; j++) {
                cin >> other.data[i][j];
            }
        }

        return is;
    }

    int* operator[](int r) {
        return data[r];
    }

    void operator=(const Table& other) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = other.data[i][j];
            }
        }
    }

    friend ostream& operator<<(ostream& os, const Table& t) {
        for (int i = 0; i < t.rows; i++) {
            for (int j = 0; j < t.cols; j++) {
                os << t.data[i][j] << " ";
            }
            os << endl;
        }
        return os;
    }
};

int main() {
    Table t(4, 5), t1(4, 5);
    cin >> t;

    t[0][0] = 5;
    int x = t[2][3];

    t1 = t;
    cout << t << "\n" << t1 << endl;

    return 0;
}
