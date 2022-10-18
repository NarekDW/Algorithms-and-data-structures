package _2_Sorting._2_1_Elementary_Sorts;

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

    public static boolean isSorted(Comparable<?>[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
}
