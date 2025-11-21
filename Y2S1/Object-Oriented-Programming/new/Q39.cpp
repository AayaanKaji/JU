#include <iostream>

using namespace std;

class Base1 {
public:

};

class Base2 {
public:

};

class Derived : public Base1, public Base2 {
public:
    
};


int main() {
    Base1 b1;
    Base2 b2;

    Derived d1;

    return 0;
}