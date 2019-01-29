package _2_Sorting._2_2_Mergesort.creative;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

/*****************************************************************************************************
 *
 * 2.2.20 Indirect sort. Develop and implement a version of mergesort that does not re-
 * arrange the array, but returns an int[] array perm such that perm[i] is the index of the
 * i th smallest entry in the array.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class IndirectSort {

    public static int[] sort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;
        int[] aux = new int[n];

        sort(a, aux, index, 0, n - 1);

        return index;
    }

    private static void sort(Comparable[] a, int[] aux, int[] index, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, index, lo, mid);
        sort(a, aux, index, mid + 1, hi);
        merge(a, aux, index, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int[] aux, int[] index, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = index[k];

        for (int k = lo; k <= hi; k++)
            if (j > hi)                             index[k] = aux[i++];
            else if (i > mid)                       index[k] = aux[j++];
            else if (less(a[aux[j]], a[aux[i]]))    index[k] = aux[j++];
            else                                    index[k] = aux[i++];
    }


    public static void main(String[] args) {
        Integer[] x = {1, 2, 4, 3, 5};
        int[] indx = sort(x);
        for (int i : indx)
            System.out.print(i + " ");
    }
}
