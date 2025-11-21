#include <iostream>
#include <cmath>
#include <vector>
#include <random>
using namespace std;

class Point {
public:
    double x;
    double y;

    Point(double x, double y) : x(x), y(y) {}

    double distance(const Point& other) const {
        double dx = x - other.x;
        double dy = y - other.y;
        return sqrt(dx * dx + dy * dy);
    }
};

class WirelessDevice {
private:
    static int nextId;
    int id;
    Point location;
    double range;

public:
    WirelessDevice(double x, double y, double range)
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

    vector<WirelessDevice> findNeighbors(const vector<WirelessDevice>& devices) const {
        vector<WirelessDevice> neighbors;
        for (const WirelessDevice& device : devices) {
            if (device.getId() != id && location.distance(device.getLocation()) <= range) {
                neighbors.push_back(device);
            }
        }
        return neighbors;
    }

    void updateLocation(double x, double y) {
        location.x = x;
        location.y = y;
    }
};

int WirelessDevice::nextId = 1;

int main() {
    mt19937 mt(50);
    uniform_int_distribution<int> dist(1, 100);

    vector<WirelessDevice> devices;

    for (int i = 0; i < 10; i++) {
        double x = dist(mt);
        double y = dist(mt);
        double range = dist(mt) + 20;
        WirelessDevice device(x, y, range);
        devices.push_back(device);
    }

    for (const WirelessDevice& device : devices) {
        vector<WirelessDevice> neighbors = device.findNeighbors(devices);
        cout << "Device " << device.getId() << "\nNeighbors: ";
        for (const WirelessDevice& neighbor : neighbors) {
            cout << neighbor.getId() << " ";
        }
        cout << "\n" << endl;
    }
    
    cout << "Updaing devices location." << endl;

    for (WirelessDevice& device : devices) {
        double newX = dist(mt);
        double newY = dist(mt);
        device.updateLocation(newX, newY);
    }

    for (const WirelessDevice& device : devices) {
        vector<WirelessDevice> neighbors = device.findNeighbors(devices);
        cout << "Device " << device.getId() << "\nNew Neighbors: ";
        for (const WirelessDevice& neighbor : neighbors) {
            cout << neighbor.getId() << " ";
        }
        cout << "\n" << endl;
    }

    return 0;
}