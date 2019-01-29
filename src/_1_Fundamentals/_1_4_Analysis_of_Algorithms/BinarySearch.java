package _1_Fundamentals._1_4_Analysis_of_Algorithms;

@SuppressWarnings("ALL")
public class BinarySearch {

    public static int rank(int a[], int key) {
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

    public static int lowestIndex(int a[], int key) {
        return lowestIndex(a, key, 0, a.length - 1);
    }

    private static int lowestIndex(int[] a, int key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return lowestIndex(a, key, lo, mid - 1);
        else if (key > a[mid]) return lowestIndex(a, key, mid + 1, hi);
        else if (mid == 0 || a[mid - 1] != a[mid])
            return mid;
        else
            return lowestIndex(a, key, lo, mid - 1);
    }
}
