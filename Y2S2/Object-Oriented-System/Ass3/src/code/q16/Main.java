/*
two interfaces “Fruit” and “Vegetable”
containing methods ‘hasAPeel’ and ‘hasARoot’ respectively. 

class “Tomato” implementing Fruit and Vegetable
Instantiate an object of Tomato. 
Print the details of this object.
*/
package q16;

interface Fruit {
    void hasAPeel();
}

interface Vegetable {
    void hasARoot();
}

class Tomato implements Fruit, Vegetable {
    public void hasAPeel() {
        System.out.println("I have peel.");
    }

    public void hasARoot() {
        System.out.println("I have root.");
    }

    public void printDetails() {
        System.out.println("It's-a-Me, Tomato!");
    }
}

public class Main {
    public static void main(String[] args) {
        Tomato t1 = new Tomato();
        t1.hasAPeel();
        t1.hasARoot();
        t1.printDetails();
    }
}
