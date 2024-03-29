package _2_Sorting._2_3_Quicksort.creative;

import common.StdRandom;

import static common.SortUtils.*;
import static common.StdRandom.shuffle;


/*****************************************************************************************************
 * <p>
 * 2.3.17 Sentinels. Modify the code in Algorithm 2.5 to remove both bounds checks
 * in the inner while loops. The test against the left end of the subarray is redundant since
 * the partitioning item acts as a sentinel ( v is never less than a[lo] ). To enable removal of
 * the other test, put an item whose key is the largest in the whole array into a[length-1]
 * just after the shuffle. This item will never move (except possibly to be swapped with an
 * item having the same key) and will serve as a sentinel in all subarrays involving the end
 * of the array. Note : When sorting interior subarrays, the leftmost entry in the subarray
 * to the right serves as a sentinel for the right end of the subarray.
 * <p>
 * SortCompare Results:
 * 1)
 * QuickSentinels : 14.948000000000096
 * Quick : 12.952999999999514
 * For 10000 random Doubles QuickSentinels is 0.867 times faster than Quick
 * <p>
 * 2)
 * Quick : 12.943999999999477
 * QuickSentinels : 14.919000000000114
 * For 10000 random Doubles Quick is 1.153 times faster than QuickSentinels
 * <p>
 * For 100_000
 * QuickSentinels : 10.46299999999964
 * Quick : 10.003999999999895
 * For 1000 random Doubles
 *     QuickSentinels is 0.956 times faster than Quick
 * Quick : 9.854999999999977
 * QuickSentinels : 10.642999999999555
 * For 1000 random Doubles
 *     Quick is 1.080 times faster than QuickSentinels
 ****************************************************************************************************/
public class Sentinels {

    public static <T extends Comparable<T>> void sort(T[] a) {
        shuffle(a);
        // find and move max element to the end of array to avoid right bound check in partition method
        T max = a[0];
        int maxIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (less(max, a[i])) {
                max = a[i];
                maxIndex = i;
            }
        }
        exch(a, maxIndex, a.length - 1);

        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        T v = a[lo];
        while (true) {
            //noinspection StatementWithEmptyBody
            while (less(a[++i], v)) {
            }
            //noinspection StatementWithEmptyBody
            while (less(v, a[--j])) {
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            int s = 100;
            Integer[] x = new Integer[s];
            for (int j = 0; j < s; j++)
                x[j] = StdRandom.uniform(s);

            sort(x);

            if (!isSorted(x))
                throw new RuntimeException();
        }
    }
}
