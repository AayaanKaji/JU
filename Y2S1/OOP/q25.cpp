#include <iostream>
using namespace std;

class Complex {
private:
    double real;
    double imag;

public:
    Complex() : real(0.0), imag(0.0) {}

    void setReal(double r) {
        real = r;
    }

    void setImg(double i) {
        imag = i;
    }

    double getReal() const {
        return real;
    }

    double getImg() const {
        return imag;
    }

    void disp() const {
        cout << real << " + " << "i(" << imag << ")" << endl;
    }

    Complex sum(const Complex& other) const {
        Complex result;
        result.real = real + other.real;
        result.imag = imag + other.imag;
        return result;
    }
};

int main() {
    Complex num1, num2, num3;

    num1.setReal(3.0);
    num1.setImg(4.0);

    num2.setReal(1.5);
    num2.setImg(2.5);

    num3 = num1.sum(num2);

    num1.disp();
    num2.disp();
    num3.disp();

    return 0;
}
