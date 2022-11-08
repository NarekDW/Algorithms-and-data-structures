package _2_Sorting._2_3_Quicksort;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.SortUtils.show;
import static common.StdRandom.shuffle;

public class Quick {

    public static <T extends Comparable<T>> void sort(T[] a) {
        shuffle(a);
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
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        String[] a = "SORTEXAMPLE".split("");
        sort(a);
        show(a);
    }
}
