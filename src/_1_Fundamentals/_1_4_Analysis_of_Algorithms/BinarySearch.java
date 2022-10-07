package _1_Fundamentals._1_4_Analysis_of_Algorithms;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

public class BinarySearch {

    // Comparable array Binary Search
    public static <T extends Comparable<T>> int rank(T key, T[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (less(key, a[mid])) hi = mid - 1;
            else if (less(a[mid], key)) lo = mid + 1;
            else return mid;
        }
        return -1;
    }


    public static int rank(int[] a, int key) {
        return rank(a, key, 0, a.length - 1);
    }

    public static int rank(int[] a, int key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(a, key, lo, mid - 1);
        else if (key > a[mid]) return rank(a, key, mid + 1, hi);
        else return mid;
    }

    public static int reversedRank(int[] a, int key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(a, key, mid + 1, hi);
        else if (key > a[mid]) return rank(a, key, lo, mid - 1);
        else return mid;
    }

}
