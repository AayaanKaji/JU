/*
 * Consider a file that contains a number of integers
 * Create two threads ‘producer’ and ‘consumer’
 * Producer thread will be reading the integers from the file continuously while 
 * consumer thread will add them up
 * Use proper synchronization mechanism if needed
*/

package q4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int result = 0;
    private static int currentNumber = 0;
    private static int currentNumberSign = 1;
    private static final Object lock = new Object();
    private static boolean isFileOpen;

    private static boolean isNumber(int i) {
        if(i >= 48 && i <= 57) return true;
        return false;
    }

    private static int toNumber(int i) {
        return i - 48;
    }

    public static void main(String[] args) { 
        try (FileReader fin = new FileReader("./q4/input.txt")) {
            isFileOpen = true;
            Thread producer = new Thread() {
                public void run() {
                    synchronized (lock) {
                        int i = 0;
                        try {
                            while((i = fin.read()) != -1) {
                                currentNumber = 0;
                                currentNumberSign = 1;// assume positive
                                if(i == 45) {
                                    currentNumberSign = -1;// negetive number
                                    i = fin.read();
                                }
                                if(isNumber(i)) {
                                    currentNumber += toNumber(i);
                                    while((i = fin.read()) != -1 && isNumber(i)) {
                                        currentNumber *= 10;
                                        currentNumber += toNumber(i);
                                    }
                                    currentNumber *= currentNumberSign;
                                }
                                lock.notify();
                                try {
                                    if(isFileOpen)
                                        lock.wait();
                                } catch (InterruptedException e) { e.printStackTrace(); }
                            }
                        } catch (IOException e) {
                            System.out.println("Can't read, need glasses");
                        }
                    }
                }
            };

            Thread consumer = new Thread() {
                public void run() {
                    synchronized(lock) {
                        while(isFileOpen) {
                            result += currentNumber;
                            lock.notify();
                            try {
                                if(fin.ready()) lock.wait();
                                else isFileOpen = false;
                            }
                            catch(InterruptedException e) { e.printStackTrace(); }
                            catch(IOException e) { System.out.println(e); }
                        }
                    }
                }
            };

            producer.start();
            consumer.start();


            try {
                producer.join();
                consumer.join();
            } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println("Result: " + result);
        }
        catch(FileNotFoundException e) {
            System.out.println("Where did the file go");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
