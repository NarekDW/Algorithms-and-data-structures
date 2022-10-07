package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

/*****************************************************************************************************
 * <p>
 * 1.4.10 Modify binary search so that it always returns the element with the smallest index
 * that matches the search element (and still guarantees logarithmic running time).
 *
 ****************************************************************************************************/
public class BinarySearch {

    public static int lowestIndex(int[] a, int key) {
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

    public static void main(String[] args) {
        int[] x = {1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 6};
        if (lowestIndex(x, 1) != 0)
            throw new RuntimeException(String.valueOf(0));
        if (lowestIndex(x, 2) != 4)
            throw new RuntimeException(String.valueOf(4));
        if (lowestIndex(x, 3) != 6)
            throw new RuntimeException(String.valueOf(6));
        if (lowestIndex(x, 4) != 10)
            throw new RuntimeException(String.valueOf(10));
        if (lowestIndex(x, 6) != 12)
            throw new RuntimeException(String.valueOf(12));
    }
}
