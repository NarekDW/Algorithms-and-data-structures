package _2_Sorting._2_2_Mergesort.creative;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.show;

/*****************************************************************************************************
 *
 * 2.2.11 Improvements. Implement the three improvements to mergesort that are de-
 * scribed in the text on page 275: Add a cutoff for small subarrays, test whether the array is
 * already in order, and avoid the copy by switching arguments in the recursive code.
 *
 ****************************************************************************************************/

@SuppressWarnings("ALL")
public class MergeX {

    public static int CUTT_OFF = 10;
    public static int sortedCnt = 0;

    public static void sort(Comparable[] a) {
        sortedCnt = 0;
        Comparable[] dst = a.clone();
        int n = a.length;
        sort(a, dst, 0, n - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst,  int lo, int hi) {
        if (hi - lo < CUTT_OFF) {
            insertionSort(dst, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        if (less(src[mid], src[mid + 1])){
            sortedCnt++;
            for (int k = lo; k <= hi; k++)
                dst[k] = src[k];
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        // Merge src[lo..mid] with src[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if (j > hi)                     dst[k] = src[i++];
            else if (i > mid)               dst[k] = src[j++];
            else if (less(src[j], src[i]))  dst[k] = src[j++];
            else                            dst[k] = src[i++];
    }

    private static void insertionSort(Comparable[] a, int l, int h) {
        for (int i = l + 1; i <= h; i++)
            for (int j = i; j > l && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }


    public static void main(String[] args) {
        String[] input = "MERGESORTEXAMPLE".split("");
        sort(input);
        show(input);
    }

}
