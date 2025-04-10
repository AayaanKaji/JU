/*
Implement the classes for the following inheritance hierarchies

interface “Shape”
    methods ‘area’, ‘draw’, ‘rotate’, ‘move’
 
classes “Circle” and “Rectangle” that implement ‘Shape’ 

Write a main() function
    create two “Circle” and three “Rectangle” 
    then move them
    Print the details of circles and rectangles before after moving them.
*/

package q14;

interface Shape {
    final double PI = 3.14; 

    double area();
    void draw();
    void rotate();
    void move(double x, double y);
}

class Circle implements Shape {
    private double radius;
    private double centerX;
    private double centerY;

    public Circle(double radius, double centerX, double centerY) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public double area() {
        return PI * (radius * radius);
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }

    @Override
    public void rotate() {
        System.out.println("Rotating circle");
    }

    @Override
    public void move(double x, double y) {
        this.centerX = x;
        this.centerY = y;
        System.out.println("Moving Circle to (" + centerX + ", " + centerY + ")");
    }

    public void printDetails() {
        System.out.println("\nCircle Details:");
        System.out.println("Radius: " + radius);
        System.out.println("Center X: " + centerX);
        System.out.println("Center Y: " + centerY);
        System.out.println("Area: " + area());
        System.out.println();
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;
    private double centerX;
    private double centerY;

    public Rectangle(double width, double height, double centerX, double centerY) {
        this.width = width;
        this.height = height;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

    @Override
    public void rotate() {
        System.out.println("Rotating Rectangle");
    }

    @Override
    public void move(double x, double y) {
        this.centerX = x;
        this.centerY = y;
        System.out.println("Moving Rectangle to (" + centerX + ", " + centerY + ")");
    }

    public void printDetails() {
        System.out.println("\nRectangle Details:");
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Top Left X: " + centerX);
        System.out.println("Top Left Y: " + centerY);
        System.out.println("Area: " + area());
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Circle c1 = new Circle(5, 0, 0);
        Circle c2 = new Circle(8, 2, 3);

        Rectangle r1 = new Rectangle(10, 5, 1, 1);
        Rectangle r2 = new Rectangle(8, 6, 3, 2);
        Rectangle r3 = new Rectangle(12, 7, 0, 0);

        c1.printDetails();
        c2.printDetails();
        r1.printDetails();
        r2.printDetails();
        r3.printDetails();

        c1.move(2, 3);
        c2.move(1, -2);
        r1.move(-1, 4);
        r2.move(3, -1);
        r3.move(2, 2);

        c1.printDetails();
        c2.printDetails();
        r1.printDetails();
        r2.printDetails();
        r3.printDetails();
    }
}
