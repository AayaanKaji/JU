#include <iostream>
using namespace std;

class Stack {
    int *buffer;
    int top;
    int size;
public :
    Stack(int size) : size(size), top(-1) {
        buffer = new int[size];
    }

    void push(int data) {
        if(top == size - 1) {
            cout << "Stack is full." << endl;
            exit(1);
        }
        buffer[++top] = data;
    }

    int pop() {
        if(top == -1) {
            cout << "Stack is empty." << endl;
            exit(1);
        }
        return buffer[top--];
    }

    void disp() const {
        for(int i=top; i>-1; i--) {
            cout << buffer[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    Stack s1(10);
    s1.push(1);
    s1.push(3);
    s1.push(2);
    s1.push(4);
    s1.disp();

    s1.pop();
    s1.disp();
    
    return 0;
}
