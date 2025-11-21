#include <iostream>
#include <cmath>

using namespace std;

class Complex {
private:
    double real;
    double imag;

public:
    Complex() : real(0.0), imag(0.0) {}
    Complex(double r, double i) : real(r), imag(i) {}

    double getReal() const {
        return real;
    }

    double getImag() const {
        return imag;
    }

    friend ostream& operator<<(ostream& os, const Complex& c) {
        os << c.real;
        if (c.imag >= 0) os << " + " << c.imag << "i";
        else os << " - " << -c.imag << "i";
        return os;
    }
};

class Quadratic {
private:
    double a;
    double b;
    double c;

public:
    Quadratic() : a(0), b(0), c(0) {}
    Quadratic(double a, double b, double c) : a(a), b(b), c(c) {}

    friend Quadratic operator+(const Quadratic& q1, const Quadratic& q2) {
        return Quadratic(q1.a + q2.a, q1.b + q2.b, q1.c + q2.c);
    }

    friend ostream& operator<<(ostream& os, const Quadratic& q) {
        os << q.a << "x^2 + " << q.b << "x + " << q.c;
        return os;
    }

    friend istream& operator>>(istream& is, Quadratic& q) {
        is >> q.a >> q.b >> q.c;
        return is;
    }

    double getValue(double x) const {
        return (a * x * x) + (b * x) + c;
    }

    void getRoots(Complex& r1, Complex& r2) const {
        double discriminant = b * b - 4 * a * c;
        if (discriminant >= 0) {
            double sqrtD = sqrt(discriminant);
            r1 = Complex((-b + sqrtD) / (2 * a), 0);
            r2 = Complex((-b - sqrtD) / (2 * a), 0);
        } else {
            double real = -b / (2 * a);
            double imag = sqrt(-discriminant) / (2 * a);
            r1 = Complex(real, imag);
            r2 = Complex(real, -imag);
        }
    }
};

int main() {
    Quadratic poly1, poly2, result;
    Complex root1, root2;

    cout << "Enter coefficients for the first polynomial (a b c): ";
    cin >> poly1;

    cout << "Enter coefficients for the second polynomial (a b c): ";
    cin >> poly2;

    result = poly1 + poly2;

    cout << "Sum of the polynomials: " << result << endl;

    double x;
    cout << "Enter a value for x to compute the values of the polynomials: ";
    cin >> x;

    cout << "Value of the first polynomial at x = " << x << " : " << poly1.getValue(x) << endl;
    cout << "Value of the second polynomial at x = " << x << " : " << poly2.getValue(x) << endl;

    poly1.getRoots(root1, root2);
    cout << "Roots of the first polynomial: " << root1 << " and " << root2 << endl;

    poly2.getRoots(root1, root2);
    cout << "Roots of the first polynomial: " << root1 << " and " << root2 << endl;

    return 0;
}
