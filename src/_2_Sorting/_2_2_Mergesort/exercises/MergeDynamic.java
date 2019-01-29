package _2_Sorting._2_2_Mergesort.exercises;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.show;

@SuppressWarnings("ALL")
public class MergeDynamic {

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        sort(a, 0, n - 1, aux);
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if (j > hi)                     a[k] = aux[i++];
            else if (i > mid)               a[k] = aux[j++];
            else if (less(aux[i], aux[j]))  a[k] = aux[i++];
            else                            a[k] = aux[j++];
    }

    public static void main(String[] args) {
        String[] input = "MERGESORTEXAMPLE".split("");
        sort(input);
        show(input);
    }

}
