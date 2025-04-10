/*
 * Class:
 *  Room -> height, width, breadth, volume()
 *  RoomDemo -> uses 'Room'
 * create instances of rooms and calculate volume    
*/

package q1;

class Room {
    // dimensions are in meter
    private double height;
    private double width;
    private double breadth;

    public Room() {
        this.height = 1;
        this.width = 1;
        this.breadth = 1;
    }

    public Room(double height, double width, double breadth) {
        this.height = height;
        this.width = width;
        this.breadth = breadth;
    }

    public double volume() {
        return height * width * breadth;
    }
}

class RoomDemo {
    private static void displayRoom(String roomName, Room room) {
        System.out.print("Room: " + roomName);
        System.out.println("\tVolume: " + room.volume() + " cubic meters");
    }

    public static void preview() {
        Room demoRoom1 = new Room();
        Room demoRoom2 = new Room(3.5, 2.5, 4.5);
        Room demoRoom3 = new Room(2.0, 3.0, 2.5);

        displayRoom("Demo 1", demoRoom1);
        displayRoom("Demo 2", demoRoom2);
        displayRoom("Demo 3", demoRoom3);
    }
}

public class Q1 {
    public static void main(String[] args) {
        Room r1 = new Room();
        Room r2 = new Room(3.0, 2.0, 4.5);

        System.out.println("Volume of Room 1: " + r1.volume() + " cubic meters");
        System.out.println("Volume of Room 2: " + r2.volume() + " cubic meters");

        RoomDemo.preview();
    }
}