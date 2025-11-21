/*
 * series x = 1 + 1/1! + 1/2! + 1/3! + ......... 1/10!
 * Create two threads t1 & t2
 * t1 will generate the denominators and t2 will form the term and add them up
 * Finally print the result
*/

package q3;

public class Main {
    private final static int n = 11; // series terms
    private final static Object lock = new Object();
    private static long currentDenominator = 0l;
    private static Double result = 0.0;

    private static long factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n-1);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                synchronized (lock) {
                    for(int i=0; i<n; i++) {
                        currentDenominator = factorial(i);
                        lock.notify();
                        try { if(i < n - 1) lock.wait(); } catch(Exception e) {}
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                synchronized (lock) {
                    for(int i=0; i<n; i++) {
                        while(currentDenominator == 0) {
                            try { lock.wait(); } catch(Exception e) {}
                        }
                        result += 1.0 / currentDenominator;
                        currentDenominator = 0;
                        lock.notify();
                    }
                }
            }
        };

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(Exception e) {}

        System.out.println("Result: " + result);
    }
}
