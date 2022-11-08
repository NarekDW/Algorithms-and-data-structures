package _2_Sorting._2_3_Quicksort.exercises;

import static common.SortUtils.exch;
import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.3.4 Suppose that the initial random shuffle is omitted.
 * Give six arrays of ten elements for which Quick.sort() uses the worst-case number of compares.
 *
 ****************************************************************************************************/
public class QuickSortCompareCounter {
    private static int compares = 0;

    public static <T extends Comparable<T>> void sort(T[] a) {
        compares = 0;
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        T v = a[lo];
        while (true) {
            do {
                compares++;
                if (i == hi) break;
            } while (less(a[++i], v));

            do {
                compares++;
                if (j == lo) break;
            } while (less(v, a[--j]));

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        // Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // 63
        // Integer[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; // 63
        // Integer[] a = {10, 2, 3, 4, 5, 6, 7, 8, 9, 1}; // 63
        // Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 10, 9}; // 63
        // Integer[] a = {1, 2, 3, 4, 5, 6, 7, 10, 9, 8}; // 63
        // Integer[] a = {1, 2, 3, 4, 5, 6, 10, 8, 9, 7}; // 63
        Integer[] a = {1, 2, 3, 4, 5, 10, 7, 8, 9, 6}; // 63
        // and so on

        sort(a);
        System.out.println(compares);
    }

}
