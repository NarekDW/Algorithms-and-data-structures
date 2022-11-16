package _2_Sorting._2_3_Quicksort.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.StdRandom.shuffle;

/**
 *
 * 1) n = 1_000
 * M = 10, n = 1_000;
 * QuickX : 1.151999999999984
 * Quick : 1.2979999999999678
 * For 1000 random Doubles QuickX is 1.127 times faster than Quick
 * <p>
 * M = 20, n = 1_000;
 * QuickX : 1.1759999999999813
 * Quick : 1.3089999999999666
 * For 1000 random Doubles QuickX is 1.113 times faster than Quick
 * <p>
 * M = 30, n = 1_000;
 * QuickX : 1.1819999999999806
 * Quick : 1.3179999999999656
 * For 1000 random Doubles QuickX is 1.115 times faster than Quick
 * <p>
 * M = 40, n = 1_000;
 * QuickX : 1.233999999999975
 * Quick : 1.313999999999966
 * For 1000 random Doubles QuickX is 1.065 times faster than Quick
 * <p>
 * M = 50, n = 1_000;
 * QuickX : 1.2909999999999686
 * Quick : 1.295999999999968
 * For 1000 random Doubles QuickX is 1.004 times faster than Quick
 * <p>
 * 2) n = 10_000
 * M = 10, n = 10_000;
 * QuickX : 14.917999999999997
 * Quick : 16.655000000000705
 * For 10000 random Doubles QuickX is 1.116 times faster than Quick
 * <p>
 * M = 20, n = 10_000;
 * QuickX : 15.927000000000522
 * Quick : 17.167000000000687
 * For 10000 random Doubles QuickX is 1.078 times faster than Quick
 * <p>
 * M = 30, n = 10_000;
 * QuickX : 15.425000000000335
 * Quick : 16.705000000000677
 * For 10000 random Doubles QuickX is 1.083 times faster than Quick
 * <p>
 * M = 40, n = 10_000;
 * M = 50, n = 10_000;
 *
 */
public class QuickX {

    public static int M = 10;

    public static <T extends Comparable<T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi);
            return;
        }

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

}
