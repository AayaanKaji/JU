import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Q3 {
    private static class Gen {
        public static <T> void foundDuplicateElements(T[] array) {
            int n = array.length;
            Set<T> set = new HashSet<>();
            Set<T> duplicateSet = new HashSet<>();

            for (T i : array) {
                if (set.contains(i)) {
                    duplicateSet.add(i);
                    continue;
                }
                set.add(i);
            }
            printSet(duplicateSet);
        }

        private static <T> void printSet(Set<T> set) {
            Iterator<T> itr = set.iterator();
            System.out.print("Duplicate elements: [");
            while(itr.hasNext()) {
                System.out.print(itr.next() + " ");
            }
            System.out.println("]");
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
        Character[] charArray = {'a', 'z', 'A', 'd', 'z', 'a'};
        String[] stringArray = {"AA", "Aa", "Aa", "aA", "Z", "Z", "l"};
        Double[] doubleArray = {1.2, 1.2, 1.4, 3.8, 3.8};

        System.out.print("Array 1: ");
        printArray(intArray);
        Gen.foundDuplicateElements(intArray);
        System.out.println();

        System.out.print("Array 2: ");
        printArray(charArray);
        Gen.foundDuplicateElements(charArray);
        System.out.println();

        System.out.print("Array 3: ");
        printArray(stringArray);
        Gen.foundDuplicateElements(stringArray);
        System.out.println();

        System.out.print("Array 4: ");
        printArray(doubleArray);
        Gen.foundDuplicateElements(doubleArray);
    }
}
