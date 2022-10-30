package common;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _2_Sorting._2_2_Mergesort.creative.MergeX;

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
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static <T extends Comparable<T>> boolean isSorted(Queue<T> queue) {
        if (queue.isEmpty())
            return true;

        T prev = queue.dequeue();
        while (!queue.isEmpty()) {
            T curr = queue.dequeue();
            if (less(curr, prev))
                return false;
            prev = curr;
        }
        return true;
    }

    public static Double[] generateArrayDouble(int size, boolean sorted) {
        Double[] arr = new Double[size];
        for (int i = 0; i < size; i++)
            arr[i] = StdRandom.uniform();
        if (sorted)
            MergeX.sort(arr);
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
            Double[] doubles = generateArrayDouble(arraySize, false);
            sortFunction.accept(doubles);
            if (!isSorted(doubles))
                throw new RuntimeException();
        }
    }

    @SuppressWarnings("unused")
    public static <T> void showQueue(Queue<T> queue) {
        for (T a : queue) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
