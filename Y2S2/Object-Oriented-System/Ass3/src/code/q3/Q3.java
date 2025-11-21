/*
    Implement a class for stack of integers using an array.
    operations defined for a stack data structure are as follows:
        "push", "pop", "print"
    * implement a constructor to create an array of integers
    * the size of the array is provided by the user
    Write a main() function to
        * create a stack to hold maximum of 30 integers
        * push the numbers 10, 20, 30, 15, 9 to the stack
        * print the stack
        * pop thrice and
        * print the stack again
*/

package q3;

class Stack {
    private int[] arr;
    private int index;
    private final int DEFAULT_SIZE = 10;

    public Stack() {
        this.arr = new int[DEFAULT_SIZE];
        index = -1;
    }

    public Stack(int stackSize) {
        this.arr = new int[stackSize];
        index = -1;
    }

    public void push(int val) {
        if(index == arr.length - 1) {
            System.out.println("Stack is full.");
            return;
        }

        arr[++index] = val;
    }

    public int pop() {
        if(index == -1) {
            System.out.println("Stack is empty.");
            return -1;
        }

        return arr[index--];
    }

    public void print() {
        if(index == -1) {
            System.out.println("Stack is empty.");
            return;
        }

        for(int i=0; i<=index; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("<-> ");// to help identify where top is
    }
}

public class Q3 {
    public static void main(String[] args) {
        Stack S = new Stack(30);

        S.push(10);
        S.push(20);
        S.push(30);
        S.push(15);
        S.push(9);

        S.print();

        S.pop();
        S.pop();
        S.pop();

        S.print();
    }
}
