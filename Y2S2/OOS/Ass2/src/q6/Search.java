/*
 * following parallel binary search algorithm for series a1, a2...an sorted in increasing order such that n mod 10 = 0
 * Element to be searched is e
 *  a) Create n/10 threads t1, t2,..,tn/10
 *  b) Distribute the numbers among threads such that ti will have numbers (ai, ai+1, ....a2i-1) -> a((i-1)*10+1)...a((i-1)*10+10)
 *  c) Distribute the element e to all threads
 *  d) Each thread searches the element e in its sub-array using binary search algorithm
*/

package q6;

public class Search {
    private int[] array;
    private int target;
    private int numberOfThread;
    private int threadIndexOfTarget = -1;
    private int arrayIndexOfTarget = -1;

    public Search(int[] arr, int target) {
        this.array = arr;
        this.target = target;
        numberOfThread = (array.length%10 == 0) ? array.length/10 : array.length/10 + 1;
    }

    public int getNumberOfThread() {
        return numberOfThread;
    }

    private int binarySearch(int startIndex, int endIndex) {
        while(startIndex <= endIndex) {
            int midIndex = (endIndex + startIndex) / 2;
            if(target < array[midIndex]) {
                endIndex = midIndex - 1;
            } else if(target > array[midIndex]) {
                startIndex = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }

    private int i;
    public int[] searchElementUsingThreads() {
        Thread[] threads = new Thread[numberOfThread];

        for(i=0; i<numberOfThread; i++) {
            int startIndex = i * 10;
            int endIndex = Math.min(startIndex + 9, array.length - 1);
            threads[i] = new Thread(() -> {
                int index = binarySearch(startIndex, endIndex);
                if (index != -1) {
                    if(threadIndexOfTarget == -1) {
                        threadIndexOfTarget = i + 1;
                        arrayIndexOfTarget = index + 1;
                    }
                }
            });
            threads[i].start();
            try {
                threads[i].join();
            }
            catch (InterruptedException e) { System.out.println(e); }
        }

        return new int[]{threadIndexOfTarget, arrayIndexOfTarget}; // If not found: {-1, -1}
    }
}
