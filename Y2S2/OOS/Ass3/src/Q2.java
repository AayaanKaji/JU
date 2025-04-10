import java.util.HashMap;
import java.util.Map;

public class Q2 {
    public static <T> void elementFrequencyCalc(T[] array) {
        int n = array.length;
        Map<T, Integer> map = new HashMap<>();
        for (T i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        System.out.println("{Element, Frequency} of all elements in this array: ");
        for (T key : map.keySet()) {
            System.out.print("{" + key + ", " + map.get(key) + "} ");
        }
        System.out.println();
    }

    public static <T> void printArray(T[] array) {
        System.out.print("[");
        for (T i : array) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Integer[] intArray = {2, 5, 5, 7, 1, 1};
        Character[] charArray = {'a', 'z', 'A', 'd', 'z', 'a'};
        String[] stringArray = {"AA", "Aa", "Aa", "aA", "Z", "Z", "l"};
        Double[] doubleArray = {1.2, 1.2, 1.4, 3.8, 3.8};

        System.out.print("Array 1: ");
        printArray(intArray);
        elementFrequencyCalc(intArray);
        System.out.println();


        System.out.print("Array 2: ");
        printArray(charArray);
        elementFrequencyCalc(charArray);
        System.out.println();

        System.out.print("Array 3: ");
        printArray(stringArray);
        elementFrequencyCalc(stringArray);
        System.out.println();

        System.out.print("Array 4: ");
        printArray(doubleArray);
        elementFrequencyCalc(doubleArray);
    }
}
