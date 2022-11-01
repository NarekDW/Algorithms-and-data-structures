package _2_Sorting._2_2_Mergesort.creative;

import common.SortUtils;
import common.StdRandom;

import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.2.20 Indirect sort. Develop and implement a version of mergesort that does not rearrange
 * the array, but returns an int[] array perm such that perm[i] is the index of the
 * i th smallest entry in the array.
 *
 ****************************************************************************************************/
public class IndirectSort {

    public static <T extends Comparable<T>> int[] sort(T[] a) {
        int n = a.length;
        int[] perm = new int[n];
        for (int i = 0; i < n; i++)
            perm[i] = i;
        int[] aux = new int[n];
        sort(a, aux, perm, 0, n - 1);
        return perm;
    }

    private static <T extends Comparable<T>> void sort(T[] a, int[] aux, int[] perm, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, perm, lo, mid);
        sort(a, aux, perm, mid + 1, hi);
        merge(a, aux, perm, lo, mid, hi);
    }

    public static <T extends Comparable<T>> void merge(T[] a, int[] aux, int[] perm, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        //noinspection ManualArrayCopy
        for (int k = lo; k <= hi; k++)
            aux[k] = perm[k];

        for (int k = lo; k <= hi; k++)
            if (j > hi)                             perm[k] = aux[i++];
            else if (i > mid)                       perm[k] = aux[j++];
            else if (less(a[aux[j]], a[aux[i]]))    perm[k] = aux[j++];
            else                                    perm[k] = aux[i++];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int size = StdRandom.uniform(0, 1000);
            Double[] doubles = SortUtils.generateArrayDouble(size, false);
            int[] perm = sort(doubles);
            if (!isSorted(doubles, perm))
                throw new RuntimeException();
        }
    }

    private static boolean isSorted(Double[] arr, int[] perm) {
        int n = perm.length;
        if (n < 2)
            return true;

        for (int i = 1; i < n; i++)
            if (less(arr[perm[i]], arr[perm[i - 1]]))
                return false;

        return true;
    }
}
