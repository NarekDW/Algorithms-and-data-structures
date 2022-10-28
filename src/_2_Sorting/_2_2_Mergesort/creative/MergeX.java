package _2_Sorting._2_2_Mergesort.creative;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.SortUtils;
import common.StdRandom;

import static common.SortUtils.less;


/*****************************************************************************************************
 * <p>
 * 2.2.11 Improvements.
 * Implement the three improvements to mergesort that are described in the text on page 275:
 * Add a cutoff for small subarrays, test whether the array is already in order, and avoid
 * the copy by switching arguments in the recursive code.
 *
 ****************************************************************************************************/
@SuppressWarnings("rawtypes")
public class MergeX {
    private static final int CUT_OFF = 10;
    public static int sortedCnt = 0;

    public static void sort(Comparable[] array) {
        sortedCnt = 0;
        Comparable[] copy = array.clone();
        sort(copy, array, 0, array.length - 1);
    }

    private static void sort(Comparable[] source, Comparable[] copy, int lo, int hi) {
        if (hi - lo < CUT_OFF) {
            Insertion.sort(copy, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(copy, source, lo, mid);
        sort(copy, source, mid + 1, hi);

        if (less(source[mid], source[mid + 1])) {
            sortedCnt++;
            System.arraycopy(source, lo, copy, lo, hi + 1 - lo);
            return;
        }

        merge(source, copy, lo, mid, hi);
    }

    public static void merge(Comparable[] source, Comparable[] destination, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if (j > hi)                             destination[k] = source[i++];
            else if (i > mid)                       destination[k] = source[j++];
            else if (less(source[j], source[i]))    destination[k] = source[j++];
            else                                    destination[k] = source[i++];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int arraySize = StdRandom.uniform(10_000, 100_000);
            SortUtils.testRandomArray(MergeX::sort, 2, arraySize);
        }
    }
}