package _2_Sorting;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.SortUtils;

import java.util.Arrays;

import static common.SortUtils.less;

/**
 * Simplified implementation of TimSort
 */
public class TimSort {
    private static final int SUB_ARRAY_SIZE = 5;
    @SuppressWarnings("rawtypes")
    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] integers = SortUtils.generateArrayInteger(17);
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        int n = a.length;

        for (int i = 0; i < n; i += SUB_ARRAY_SIZE) {
            Insertion.sort(a, i, Math.min(i + SUB_ARRAY_SIZE - 1, n - 1));
        }

        aux = new Comparable[n];
        for (int sz = SUB_ARRAY_SIZE; sz < n; sz = sz + sz) // sz: subarray size
            for (int lo = 0; lo < n - sz; lo += sz + sz) // lo: subarray index
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(T[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++)
            if (j > hi) a[k] = (T) aux[i++];
            else if (i > mid) a[k] = (T) aux[j++];
            else if (less(aux[j], aux[i])) a[k] = (T) aux[j++];
            else a[k] = (T) aux[i++];
    }

}