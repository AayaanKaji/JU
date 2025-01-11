#include <iostream>

using namespace std;

class Integer {
    int a;
    
public:
    Integer(int a = 0): a(a) {}

    ~Integer() {}

    Integer& operator++(int) {
        a++;
        return *this;
    }

    Integer operator+(const Integer& other) const {
        return Integer(a + other.a);
    }

    operator int() {
        return a;
    }

    friend ostream& operator<<(ostream& os, const Integer& t) {
        os << t.a;
        return os;
    }
};

int main() {
    Integer a = 4, b = a, c;
    c = a + b++;
    int i = a;
    cout << a << b << c;
    
    return 0;
}