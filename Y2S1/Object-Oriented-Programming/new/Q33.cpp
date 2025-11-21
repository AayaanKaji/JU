#include <iostream>

using namespace std;

class INT {
    int i;
public:
    INT(int a) : i(a){}

    ~INT() {}

    INT operator++(int) {
        INT copy(*this);
        i++;
        return copy;
    }

    INT& operator++() {
        i++;
        return *this;
    }

    operator int() const {
        return i;
    }
};

int main() {
    int x = 3;
    INT y = x;
    y++ = ++y;
    x = y;

    return 0;
}
