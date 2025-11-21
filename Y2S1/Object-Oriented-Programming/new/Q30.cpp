#include <iostream>
#include <cmath>

class Point {
private:
    double x;
    double y;

public:
    Point(double x, double y) : x(x), y(y) {}

    ~Point() {}

    double getX() const {
        return x;
    }

    double getY() const {
        return y;
    }

    void setX(double X) {
        x = X;
    }

    void setY(double Y) {
        y = Y;
    }

    double operator-(const Point& other) const {
        double dx = x - other.x;
        double dy = y - other.y;
        return sqrt(dx * dx + dy * dy);
    }
};

int main() {
    Point p1(1, 2);
    Point p2(4, 6);

    std::cout << "Distance between points: " << (p1 - p2) << std::endl;

    return 0;
}
