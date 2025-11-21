/*
toll booth with a toll tax 50/- and a bridge

booth keeps track of 
    car's number which have't paid,
    total  number  of  cars  passed  by, 
    the  total  amount  of money collected
    
Execute  this with a class called “Tollbooth” and 
print out the result as follows:
    The total number of cars passed by without paying.
    Total number of cars passed by.
    Total cash collected.
*/

package q15;

import java.util.Arrays;
import java.util.List;

class TollBooth {
    private List<Integer> listOfCarsTollStatus; // 1: have paid; 0: not paid
    private int totalCarsPassed;
    private int totalCarsHaveNotPaid;
    private int tollCollected;
    private static final int TOLL_TAX = 50;

    public TollBooth(Integer[] listOfCarsTollStatus) {
        this.listOfCarsTollStatus = Arrays.asList(listOfCarsTollStatus);
        this.totalCarsPassed = listOfCarsTollStatus.length;
        this.totalCarsHaveNotPaid = carsThatHaveNotPaid();
        this.tollCollected = calculateToll();
    }

    public int getTotalCarsPassed() {
        return totalCarsPassed;
    }

    public int getTotalCarsNotPaid() {
        return totalCarsHaveNotPaid;
    }

    public int getTollCollected() {
        return tollCollected;
    }

    public void addCar(int carTollStatus) {
        listOfCarsTollStatus.add(carTollStatus);
        totalCarsPassed++;
        totalCarsHaveNotPaid += carTollStatus;
        tollCollected += carTollStatus * TOLL_TAX;
    }

    private int carsThatHaveNotPaid() {
        int temp = 0;
        for(Integer status : listOfCarsTollStatus) {
            if(status == 0)
                temp++;
        }
        return temp;
    }

    private int calculateToll() {
        return (totalCarsPassed - totalCarsHaveNotPaid) * TOLL_TAX;
    }

    public void printDetails() {
        System.out.println("The total number of cars passed by without paying: " + totalCarsHaveNotPaid);
        System.out.println("Total number of cars passed by: " + totalCarsPassed);
        System.out.println("Total cash collected: Rs. " + tollCollected);
    }
}

public class Main {
    public static void main(String[] args) {
        Integer[] c1 = {1, 0, 1, 1, 0, 1, 0};

        TollBooth tb = new TollBooth(c1);
        tb.printDetails();

        // Integer[] c2 = {0, 1, 1, 0, 1};
        // for (Integer car : c2) {
        //     tb.addCar(car);
        // }

        //tb.printDetails();
    }
}
