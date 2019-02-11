package _2_Sorting._2_5_Sorting_Applications.exercises;

import common.StdRandom;

import static _2_Sorting._2_3_Quicksort.Quick.partition;

/*****************************************************************************************************
 *
 * 2.5.6 Implement a recursive version of select()
 *
 ****************************************************************************************************/
public class KSmallest {

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        return select(a, k, 0, a.length - 1);
    }

    public static Comparable select(Comparable[] a, int k, int lo, int hi) {
        if (hi <= lo) return a[k];
        int j = partition(a, lo, hi);
        if (j == k) return a[k];
        else if (j > k) return select(a, k, lo, j - 1);
        else return select(a, k, j + 1, hi);
    }

    public static Comparable select2(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j == k) return a[k];
            else if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
        }

        return a[k];
    }

    public static void main(String[] args) {
        Integer[] x = {3, 2, 1, 7, 8, 10};
        Comparable k = select(x, 3);
        Comparable k2 = select2(x, 3);
        System.out.println(k);
        System.out.println(k2);


    }
}
