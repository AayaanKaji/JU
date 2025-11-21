#include <iostream>
#include <cmath>
#include <random>
using namespace std;

class Point {
    double x;
    double y;
public:
    Point(double x, double y) : x(x), y(y) {}

    double distance(const Point& o) const {
        double dx = x - o.x;
        double dy = y - o.y;
        return sqrt(dx * dx + dy * dy);
    }

    void updatePoint(double x, double y) {
        this->x = x;
        this->y = y;
    }
};

class WirelessDevice {
private:
    static int nextId;
    int id;
    Point location;
    double range;

public:
    WirelessDevice(double x=0, double y=0, double range=10)
        : id(nextId++), location(x, y), range(range) {}

    int getId() const {
        return id;
    }

    Point getLocation() const {
        return location;
    }

    double getRange() const {
        return range;
    }

    void findNeighbors(const WirelessDevice devices[], int num, int &numNeigh, int neighIds[]) const {
        numNeigh = 0;
        for (int i = 0; i < num; i++) {
            if (devices[i].getId() != id &&
                location.distance(devices[i].getLocation()) <= range) {
                neighIds[numNeigh++] = devices[i].getId();
            }
        }
    }

    void updateLocation(double x, double y) {
        location.updatePoint(x, y);
    }
};

int WirelessDevice::nextId = 1;

int main() {
    mt19937 mt(50);
    uniform_int_distribution<int> dist(1, 100);

    const int num = 10;
    WirelessDevice devices[num];

    for (int i = 0; i < num; i++) {
        double x = dist(mt);
        double y = dist(mt);
        double range = dist(mt) + 20;
        WirelessDevice device(x, y, range);
        devices[i] = device;
    }


    for (int i = 0; i < num; i++) {
        int neighIds[num - 1];
        int numNeigh;
        devices[i].findNeighbors(devices, num, numNeigh, neighIds);
        cout << "Device " << devices[i].getId() << "\nNeighbors: ";
        for (int j = 0; j < numNeigh; j++) {
            cout << neighIds[j] << " ";
        }
        cout << "\n" << endl;
    }

    cout << "Updating devices' locations." << endl;

    for (int i = 0; i < num; i++) {
        double newX = dist(mt);
        double newY = dist(mt);
        devices[i].updateLocation(newX, newY);
    }

    for (int i = 0; i < num; i++) {
        int neighIds[num - 1];
        int numNeigh;
        devices[i].findNeighbors(devices, num, numNeigh, neighIds);
        cout << "Device " << devices[i].getId() << "\nNew Neighbors: ";
        for (int j = 0; j < numNeigh; j++) {
            cout << neighIds[j] << " ";
        }
        cout << "\n" << endl;
    }

    return 0;
}