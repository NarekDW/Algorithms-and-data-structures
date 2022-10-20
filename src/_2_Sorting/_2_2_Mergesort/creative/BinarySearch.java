package _2_Sorting._2_2_Mergesort.creative;

import static common.SortUtils.less;

public class BinarySearch {

    public static <T extends Comparable> int rank(Comparable[] a, Comparable key) {
        return rank(a, key, 0, a.length - 1);
    }

    public static <T extends Comparable> int rank(Comparable[] a, Comparable key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (less(key, a[mid])) return rank(a, key, lo, mid - 1);
        else if (less(a[mid], key)) return rank(a, key, mid + 1, hi);
        else return mid;
    }

}
