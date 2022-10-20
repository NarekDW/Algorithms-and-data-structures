package _2_Sorting._2_3_Quicksort.exercises;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.SortUtils.show;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 *
 * 2.3.9 Explain what happens when Quick.sort() is run on an array having items with
 * just two distinct keys, and then explain what happens when it is run on an array having
 * just three distinct keys.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class QuickSortTest {

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] x  = {2, 1, 2, 1, 2, 2, 2, 1, 1, 1, 2};
        sort(x);
        show(x);
    }
}
