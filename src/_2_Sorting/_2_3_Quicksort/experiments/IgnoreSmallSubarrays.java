package _2_Sorting._2_3_Quicksort.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 * <p>
 * 2.3.27 Ignore small subarrays. Run experiments to compare the following strategy for
 * dealing with small subarrays with the approach described in Exercise 2.3.25: Simply
 * ignore the small subarrays in quicksort, then run a single insertion sort after the quick-
 * sort completes. Note : You may be able to estimate the size of your computer’s cache
 * memory with these experiments, as the performance of this method is likely to degrade
 * when the array does not fit in the cache.
 *
 ****************************************************************************************************/
public class IgnoreSmallSubarrays {

    private static final int M = 20;

    public static <T extends Comparable<T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
        Insertion.sort(a);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo + M) return;
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


    public static void main(String[] args) {
        /*
        Quick : 14.531999999999877
        IgnoreSmallSubarrays : 13.45199999999962
        For 10_000 random Doubles Quick is 0.926 times faster than IgnoreSmallSubarrays
         */
//        SortCompare.run("Quick", "IgnoreSmallSubarrays", 10_000, 10_000, false);
//        SortCompare.run("Quick", "IgnoreSmallSubarrays", 10_000, 10_000, false);


        /*
        Quick : 22.65599999999977
        IgnoreSmallSubarrays : 22.47399999999984
        For 100_000 random Doubles Quick is 0.992 times faster than IgnoreSmallSubarrays
         */
//        SortCompare.run("Quick", "IgnoreSmallSubarrays", 100_000, 1_000, false);
//        SortCompare.run("Quick", "IgnoreSmallSubarrays", 100_000, 1_000, false);

        /*
        IgnoreSmallSubarrays : 53.003999999999984
        QuickX : 47.58399999999999
        For 1_000_000 random Doubles IgnoreSmallSubarrays is 0.898 times faster than QuickX
         */
//        SortCompare.run("IgnoreSmallSubarrays", "QuickX", 1_000_000, 100, false);
//        SortCompare.run("IgnoreSmallSubarrays", "QuickX", 1_000_000, 100, false);

        /*
        IgnoreSmallSubarrays : 52.68200000000001
        Quick : 48.50099999999998
        For 1_000_000 random Doubles IgnoreSmallSubarrays is 0.921 times faster than Quick
         */
//        SortCompare.run("IgnoreSmallSubarrays", "Quick", 1_000_000, 100, false);
//        SortCompare.run("IgnoreSmallSubarrays", "Quick", 1_000_000, 100, false);

        /*
        IgnoreSmallSubarrays : 13.261999999999542
        QuickX : 14.027999999999775
        For 10000 random Doubles IgnoreSmallSubarrays is 1.058 times faster than QuickX
         */
//        SortCompare.run("IgnoreSmallSubarrays", "QuickX", 10_000, 10_000, false);
//        SortCompare.run("IgnoreSmallSubarrays", "QuickX", 10_000, 10_000, false);
    }

}
