package _1_Fundamentals._1_1_Programming_Model.creative;

import common.StdRandom;

/*****************************************************************************************************
 *
 * 1.1.29 Equal keys.
 * Add to BinarySearch a static method rank() that takes a key and a sorted array of int values
 * (some of which may be equal) as arguments and returns the number of elements that are smaller than the key
 * and a similar method count() that returns the number of elements equal to the key.
 * Note : If i and j are the values returned by rank(key, a) and count(key, a) respectively,
 * then a[i..i+j-1] are the values in the array that are equal to key.
 *
 ****************************************************************************************************/
public class BinarySearch {

    static int rankHelper(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (key < a[mid]) return rankHelper(key, a, lo, mid - 1);
        else if (key > a[mid]) return rankHelper(key, a, mid + 1, hi);
        else return mid;
    }

    static int rank(int key, int[] a) {
        return rankHelper(key, a, 0, a.length - 1);
    }

    static int rankHelperLower(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (key < a[mid]) return rankHelperLower(key, a, lo, mid - 1);
        else if (key > a[mid]) return rankHelperLower(key, a, mid + 1, hi);
        else if (mid != lo && a[mid] == a[mid - 1]) return rankHelperLower(key, a, lo, mid - 1);
        else return mid;
    }

    /***
     * Thins function will return lower index in case of duplicate elements in array.
     * For example if array is {1, 2, 3, 3, 3, 4, 5} and key = 3, it will return index = 2.
     */
    static int rankLowerIndex(int key, int[] a) {
        return rankHelperLower(key, a, 0, a.length - 1);
    }

    /***
     * Testing the function above {@link #rankLowerIndex(int, int[])} rankLowerIndex}
     */
    static void checkRankLower(int n) {
        for (int i = 0; i < n; i++) {
            int z = 100;
            int[] x = new int[z];
            for (int j = 0; j < z; j++) {
                x[j] = StdRandom.uniform(n);
            }
            int key = x[StdRandom.uniform(z)];
            int index = rankLowerIndex(key, x);
            if (index != 0 && index != -1 && x[index] == x[index - 1])
                throw new RuntimeException();
        }
    }

    static int rankHelperHigher(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (key < a[mid]) return rankHelperHigher(key, a, lo, mid - 1);
        else if (key > a[mid]) return rankHelperHigher(key, a, mid + 1, hi);
        else if (mid != hi && a[mid] == a[mid + 1]) return rankHelperHigher(key, a, mid + 1, hi);
        else return mid;
    }

    /***
     * Thins function will return higher index in case of duplicate elements in array.
     * For example if array is {1, 2, 3, 3, 3, 4, 5} and key = 3, it will return index = 4.
     */
    static int rankHigherIndex(int key, int[] a) {
        return rankHelperHigher(key, a, 0, a.length - 1);
    }

    /***
     * Test the function above {@link #rankHigherIndex(int, int[]) rankHigherIndex}
     */
    static void checkRankHigher(int n) {
        for (int i = 0; i < n; i++) {
            int z = 100;
            int[] x = new int[z];
            for (int j = 0; j < z; j++) {
                x[j] = StdRandom.uniform(n);
            }
            int key = x[StdRandom.uniform(z)];
            int index = rankHigherIndex(key, x);
            if (index != z - 1 && index != -1 && x[index] == x[index + 1])
                throw new RuntimeException();
        }
    }

    // 1.1.29
    static int numberOfElementsThatAreSmallerThanTheKey(int key, int[] a) {
        return rankLowerIndex(key, a);
    }

    // 1.1.29
    static int count(int key, int[] a) {
        int h = rankHigherIndex(key, a);
        int l = rankLowerIndex(key, a);
        if (h == -1 || l == -1)
            return -1;
        return h - l + 1;
    }

    public static void main(String[] args) {
        checkRankLower(100_000);
        checkRankHigher(100_000);
        int[] a = {1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5};
        int key = 3;
        int rank = numberOfElementsThatAreSmallerThanTheKey(key, a);
        System.out.println("numberOfElementsThatAreSmallerThanTheKey: " + rank);

        int count = count(key, a);
        System.out.println("count: " + count);
    }
}
