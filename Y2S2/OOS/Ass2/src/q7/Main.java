/*
 * Write a Java program using threading technology
 * print the thread index and location where the element has been found.
*/

package q7;

import java.util.Scanner;

import q6.Search;

public class Main {
    private static final int arrayLength = 100;
    private static int target;
    private static int[] answer = new int[2];

    public static void main(String[] args) {

        int[] arr = new int[arrayLength];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.print("Enter eleme0nt to find (from 1 to " + arr[arr.length - 1] + "): ");
        Scanner sc = new Scanner(System.in);
        target = sc.nextInt();
        sc.nextLine();
        sc.close();

        Search s = new Search(arr, target);

        answer = s.searchElementUsingThreads();

        if(answer[0] == -1) {
            System.err.println("Element not found!");
        } else {
            System.out.println("Element " + target + " found at:");
            System.out.println("Thread Index: " + answer[0] + " out of " + s.getNumberOfThread() + " Threads");
            System.out.println("Location: " + answer[1] + " element out of " + arrayLength + " Elements");
        }
    }
}
