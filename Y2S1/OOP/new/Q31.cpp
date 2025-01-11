#include <iostream>

class Complex {
private:
    double real;
    double imag;

public:
    Complex(double r = 0, double i = 0) : real(r), imag(i) {}

    double getReal() const {
        return real;
    }

    double getImag() const {
        return imag;
    }

    void setReal(double r) {
        real = r;
    }

    void setImag(double i) {
        imag = i;
    }

    Complex& operator=(const Complex& other) {
        real = other.real;
        imag = other.imag;
        return *this;
    }

    Complex operator+(const Complex& other) const {
        return Complex(real + other.real, imag + other.imag);
    }

    Complex operator-(const Complex& other) const {
        return Complex(real - other.real, imag - other.imag);
    }

    Complex operator*(const Complex& other) const {
        return Complex(
            real * other.real - imag * other.imag,
            real * other.imag + imag * other.real
        );
    }

    Complex operator/(const Complex& other) const {
        double D = other.real * other.real + other.imag * other.imag;
        
        if(D == 0) {
            return Complex();
        }

        return Complex(
            (real * other.real + imag * other.imag) / D,
            (imag * other.real - real * other.imag) / D
        );
    }
};

int main() {
    Complex c1(1, 2);
    Complex c2(3, 4);

    Complex sum = c1 + c2;
    Complex diff = c1 - c2;
    Complex pdt = c1 * c2;
    Complex quotient = c1 / c2;

    std::cout << "Sum: " << sum.getReal() << " + " << sum.getImag() << "i\n";
    std::cout << "Difference: " << diff.getReal() << " + " << diff.getImag() << "i\n";
    std::cout << "Product: " << pdt.getReal() << " + " << pdt.getImag() << "i\n";
    std::cout << "Quotient: " << quotient.getReal() << " + " << quotient.getImag() << "i\n";

    return 0;
}
