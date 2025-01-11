#include <iostream>
using namespace std;

class Index {
    int x;

public:
    Index(int x = 0) : x(x) {}

    operator int() const {
        return x;
    }

    int getter() const {
        return x;
    }
};

class Integer {
    int a;

public:
    Integer(int a = 0) : a(a) {}

    Integer(const Index& other) {
        a = other.getter();
    }
};

int main() {
    Index in(4), out(10);
    int x = in;
    int y = in + out;
    in = 2;

    Integer i;
    i = in;

    return 0;
}
