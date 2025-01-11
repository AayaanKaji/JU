/*
Create two threads EvenThread and OddThread
EvenThread will print number as 2 4 6 8 10... and
Odd Thread will print number as 1 3 5....
Now, synchronize these two threads to get the output as: 1 2 3 4 5 6 7 8
*/

package q2;

public class Main {
    private static final int countTo = 10;
    private static int currentNumber = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread evenThread = new Thread() {
            public void run() {
                synchronized (lock) {
                    while(currentNumber <= countTo) {
                        System.out.print(currentNumber++ + " ");
                        lock.notify();
                        try{
                            if(currentNumber <= countTo)
                                lock.wait();
                        }
                        catch(Exception e) {}
                    }
                }
            }
        };

        Thread oddThread = new Thread() {
            public void run() {
                synchronized (lock) {
                    while(currentNumber <= countTo) {
                        System.out.print(currentNumber++ + " ");
                        lock.notify();
                        try{
                            if(currentNumber <= countTo)
                                lock.wait();
                        }
                        catch(Exception e) {}
                    }
                }
            }
        };

        oddThread.start();
        evenThread.start();

        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println();
    }
}
