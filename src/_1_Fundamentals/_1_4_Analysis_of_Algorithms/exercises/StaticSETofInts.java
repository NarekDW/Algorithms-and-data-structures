package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

import java.util.Arrays;

/*****************************************************************************************************
 *
 * 1.4.11 Add an instance method howMany() to StaticSETofInts (page 99) that finds the
 * number of occurrences of a given key in time proportional to log N in the worst case.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class StaticSETofInts {
    private int[] a;

    /**
     * Initializes a set of integers specified by the integer array.
     *
     * @param keys the array of integers
     * @throws IllegalArgumentException if the array contains duplicate integers
     */
    public StaticSETofInts(int[] keys) {

        // defensive copy
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i];

        // sort the integers
        Arrays.sort(a);

        // check for duplicates
//        for (int i = 1; i < a.length; i++)
//            if (a[i] == a[i - 1])
//                throw new IllegalArgumentException("Argument arrays contains duplicate keys.");
    }

    /**
     * Is the key in this set of integers?
     *
     * @param key the search key
     * @return true if the set of integers contains the key; false otherwise
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * Returns either the index of the search key in the sorted array
     * (if the key is in the set) or -1 (if the key is not in the set).
     *
     * @param key the search key
     * @return the number of keys in this set less than the key (if the key is in the set)
     * or -1 (if the key is not in the set).
     */
    public int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public int howMany(int key) {
        int low = lowestIndex(key, 0, a.length - 1);
        int high = highestIndex(key, 0, a.length - 1);
        if (low != -1)
            return (high - low) + 1;
        else
            return -1;
    }

    private int lowestIndex(int key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return lowestIndex(key, lo, mid - 1);
        else if (key > a[mid]) return lowestIndex(key, mid + 1, hi);
        else if (mid == 0 || a[mid - 1] != a[mid])
            return mid;
        else
            return lowestIndex(key, lo, mid - 1);
    }

    private int highestIndex(int key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return highestIndex(key, lo, mid - 1);
        else if (key > a[mid]) return highestIndex(key, mid + 1, hi);
        else if (mid == a.length - 1 || a[mid + 1] != a[mid])
            return mid;
        else
            return highestIndex(key, mid + 1, hi);
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6, 6, 6};
        StaticSETofInts ints = new StaticSETofInts(x);
        System.out.println(ints.howMany(3));
        System.out.println(ints.howMany(5));
        System.out.println(ints.howMany(6));
    }
}
