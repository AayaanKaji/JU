public class Q1 {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<n-i-1; j++) {
                if (array[j].compareTo(array[j+1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
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
        Character[] charArray = {'a', 'z', 'A', 'd', 'z', 'o'};
        String[] stringArray = {"B", "Ai", "Aa", "aA", "zx", "B"};
        Double[] doubleArray = {1.23, 4.56, 1.4, 5.65, 3.8};

        System.out.print("Before sorting: ");
        printArray(intArray);
        System.out.print("After sorting: ");
        sort(intArray);
        printArray(intArray);
        System.out.println();

        System.out.print("Before sorting: ");
        printArray(charArray);
        System.out.print("After sorting: ");
        sort(charArray);
        printArray(charArray);
        System.out.println();

        System.out.print("Before sorting: ");
        printArray(stringArray);
        System.out.print("After sorting: ");
        sort(stringArray);
        printArray(stringArray);
        System.out.println();

        System.out.print("Before sorting: ");
        printArray(doubleArray);
        System.out.print("After sorting: ");
        sort(doubleArray);
        printArray(doubleArray);
    }
}
