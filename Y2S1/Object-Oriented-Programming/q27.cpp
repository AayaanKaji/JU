#include <iostream>
using namespace std;

class Queue {
    int *data;
    int front, rear;
    int size;
public :
    Queue(int size) : size(size), front(-1), rear(-1) {
        data = new int[size];
    }

    void add(int val) {
        if((rear + 1) % size == front) {
            cout << "Queue is full." << endl;
            return;
        }
        
        if(front == -1) {
            front = rear = 0;
        }else {
            rear = (rear + 1) % size;
        }
        data[rear] = val;
    }

    int remove() {
        if(front == -1) {
            cout << "Queue is empty." << endl;
            return -1;
        }

        int val = data[front];
        if(front == rear) {
            front = rear = -1;
        }else {
            front = (front + 1) % size;
        }

        return val;
    }
    
    void disp() const {
        if(front == -1) {
            cout << "Queue is empty." << endl;
            return;
        }
        int i = front;
        while(i != rear) {
            cout << data[i] << " ";
            i = (i + 1) % size;
        }
        cout << data[i] << " ...." << endl;
    }
};

int main() {
    Queue queue(10);

    queue.add(2);
    queue.add(3);
    queue.add(4);
    queue.add(5);

    queue.disp();

    queue.remove();
    queue.remove();

    queue.disp();

    return 0;
}
