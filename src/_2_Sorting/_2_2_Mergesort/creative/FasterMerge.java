package _2_Sorting._2_2_Mergesort.creative;

import common.SortUtils;

import static common.SortUtils.*;

/*****************************************************************************************************
 * <p>
 * 2.2.10 Faster merge. Implement a version of merge() that copies the second half of
 * a[] to aux[] in decreasing order and then does the merge back to a[] .
 * This change allows you to remove the code to test that each of the halves
 * has been exhausted from the inner loop.
 * Note: The resulting sort is not stable (see page 341).
 *
 ****************************************************************************************************/
@SuppressWarnings("rawtypes")
public class FasterMerge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, n - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        //noinspection ManualArrayCopy
        for (int k = lo; k <= mid; k++)
            aux[k] = a[k];

        int tmpMid = mid + 1;
        for (int k = hi; k > mid; k--)
            aux[tmpMid++] = a[k];

        int l = lo, h = hi;
        for (int k = lo; k <= hi; k++)
            if (less(aux[l], aux[h])) a[k] = aux[l++];
            else a[k] = aux[h--];
    }


    public static void main(String[] args) {
        String[] input = "MERGESORTEXAMPLE".split("");
        sort(input);
        show(input);
        SortUtils.testRandomArray(FasterMerge::sort, 10, 1_000_000);
    }
}
