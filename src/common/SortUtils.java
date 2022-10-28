package common;

import java.util.function.Consumer;

public class SortUtils {

    public static void exch(Comparable<?>[] a, int i, int j) {
        Comparable<?> tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable<?>[] a) {
        for (Comparable<?> x : a)
            System.out.print(x + " ");
        System.out.println();
    }

    public static <T> boolean isSorted(Comparable<T>[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static Double[] generateArrayDouble(int size) {
        Double[] arr = new Double[size];
        for (int i = 0; i < size; i++)
            arr[i] = StdRandom.uniform();
        return arr;
    }

    public static int[] generateArrayInt(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = StdRandom.uniform(size);
        return arr;
    }

    public static Integer[] generateArrayInteger(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++)
            arr[i] = StdRandom.uniform(size);
        return arr;
    }

    public static void testRandomArray(Consumer<Double[]> sortFunction, int tries, int arraySize) {
        for (int i = 0; i < tries; i++) {
            Double[] doubles = generateArrayDouble(arraySize);
            sortFunction.accept(doubles);
            if (!isSorted(doubles))
                throw new RuntimeException();
        }
    }
}
