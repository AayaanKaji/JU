/*
 * series 1+2+3+...+100.  
 * This can be considered as (1+3+5+...+99)+(2+4+6+...+100)
 * Create two threads to compute two series in parallel (do not use simplified equation)
 * Finally print the final sum
*/

package q5;

class Resource {
    protected static int lastTerm;
    protected static final Object lock = new Object();
    protected static int result = 0;

    protected void printResult() {
        System.out.println("Result: " + result);
    }
}

class TOdd extends Resource implements Runnable {
    public TOdd(int lastTerm) {
        Resource.lastTerm = lastTerm;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for(int i=1; i<=lastTerm; i++) {
                if(i % 2 != 0) {
                    result += i;
                }
                lock.notify();
                try {
                    if(i < lastTerm)
                        lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if(i == lastTerm && lastTerm % 2 != 0) {
                    printResult();
                }
            }
        }
    }
}

class TEven extends Resource implements Runnable {
    public TEven(int lastTerm) {
        Resource.lastTerm = lastTerm;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for(int i=1; i<=lastTerm; i++) {
                if(i % 2 == 0) {
                    result += i;
                }
                lock.notify();
                try {
                    if(i < lastTerm)
                        lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(i == lastTerm && lastTerm % 2 == 0) {
                    printResult();
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int lastTerm = 100;
        TOdd obj1 = new TOdd(lastTerm);
        TEven obj2 = new TEven(lastTerm);

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        t2.start();
    }
}
