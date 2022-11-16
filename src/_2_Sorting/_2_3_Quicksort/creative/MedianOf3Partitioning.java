package _2_Sorting._2_3_Quicksort.creative;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.StdRandom;

import static common.SortUtils.*;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 * <p>
 * 2.3.18 Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as described
 * in the text (see page 296). Run doubling tests to determine the effectiveness of the change.
 ****************************************************************************************************/
public class MedianOf3Partitioning {

    public static <T extends Comparable<T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) return;
        moveMedianElementToTheLoIndex(a, lo, hi);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        T v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    // Move median element of 3 to the lo position
    private static <T extends Comparable<T>> void moveMedianElementToTheLoIndex(T[] a, int lo, int hi) {
        if (hi - lo + 1 > 2) {
            Insertion.sort(a, lo, lo + 2);
            exch(a, lo, lo + 1);
            exch(a, lo + 2, hi);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            int s = 1_000;
            Integer[] x = new Integer[s];
            for (int j = 0; j < s; j++)
                x[j] = StdRandom.uniform(s);

            sort(x);

            if (!isSorted(x)) {
                show(x);
                throw new RuntimeException();
            }
        }
    }
}
