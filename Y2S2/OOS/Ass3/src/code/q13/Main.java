/*
base class “Automobile”
    data  members  ‘make’,  ‘type’, ‘maxSpeed’, ‘price’, ‘mileage’, ‘registrationNumber’
    reader/writer methods

create  two  sub-classes  “Track” & “Car”
    Track  has  data  members  ‘capacity’,  ‘hoodType’, ‘noOfWheels’
    Car has data members  ‘noOfDoors’, ‘seatingCapacity’
    their reader/writer methods
    
Create a main() function to demonstrate this.
*/

package q13;

class Automobile {
    private String make;
    private String type;
    private int maxSpeed;
    private double price;
    private double mileage;
    private String registrationNumber;

    public Automobile(String make, String type, int maxSpeed, double price, double mileage, String registrationNumber) {
        this.make = make;
        this.type = type;
        this.maxSpeed = maxSpeed;
        this.price = price;
        this.mileage = mileage;
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getType() {
        return type;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getPrice() {
        return price;
    }

    public double getMileage() {
        return mileage;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void displayDetails() {
        System.out.println("Make: " + make);
        System.out.println("Type: " + type);
        System.out.println("Max Speed: " + maxSpeed);
        System.out.println("Price: $" + price);
        System.out.println("Mileage: " + mileage);
        System.out.println("Registration Number: " + registrationNumber);
    }
}

class Truck extends Automobile {
    private int capacity;
    private String hoodType;
    private int noOfWheels;

    public Truck(String make, String type, int maxSpeed, double price, double mileage, String registrationNumber,
                 int capacity, String hoodType, int noOfWheels) {
        super(make, type, maxSpeed, price, mileage, registrationNumber);
        this.capacity = capacity;
        this.hoodType = hoodType;
        this.noOfWheels = noOfWheels;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getHoodType() {
        return hoodType;
    }

    public int getNoOfWheels() {
        return noOfWheels;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Capacity: " + capacity);
        System.out.println("Hood Type: " + hoodType);
        System.out.println("Number of Wheels: " + noOfWheels);
    }
}

class Car extends Automobile {
    private int noOfDoors;
    private int seatingCapacity;

    public Car(String make, String type, int maxSpeed, double price, double mileage, String registrationNumber,
               int noOfDoors, int seatingCapacity) {
        super(make, type, maxSpeed, price, mileage, registrationNumber);
        this.noOfDoors = noOfDoors;
        this.seatingCapacity = seatingCapacity;
    }

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Number of Doors: " + noOfDoors);
        System.out.println("Seating Capacity: " + seatingCapacity);
    }
}

public class Main {
    public static void main(String[] args) {
        Truck truck = new Truck("Ford", "Truck", 120, 50000.0, 15.5, "TR12345", 2000, "Front Open", 6);

        Car car = new Car("Toyota", "Sedan", 150, 30000.0, 20.5, "CAR6789", 4, 5);

        System.out.println("Truck Details:");
        truck.displayDetails();
        System.out.println();

        System.out.println("Car Details:");
        car.displayDetails();
    }
}

