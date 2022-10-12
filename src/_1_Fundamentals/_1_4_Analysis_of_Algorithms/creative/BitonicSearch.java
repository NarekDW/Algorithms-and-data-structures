package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.BinarySearch;

/*****************************************************************************************************
 * <p>
 * 1.4.20 Bitonic search. An array is bitonic if it is comprised of an increasing sequence
 * of integers followed immediately by a decreasing sequence of integers. Write a program
 * that, given a bitonic array of N distinct int values, determines whether a given integer
 * is in the array. Your program should use ~3lg N compares in the worst case.
 *
 ****************************************************************************************************/
public class BitonicSearch {

    // ~ 3log(N)
    public static boolean contains(int[] a, int key) {
        // log(N)
        int maxElemIndex = maxRank(a, 0, a.length - 1);
        // log(N)
        int indexInFirstHalf = BinarySearch.rank(a, key, 0, maxElemIndex);
        // log(N)
        int indexInSecondHalf = BinarySearch.reversedRank(a, key, maxElemIndex + 1, a.length - 1);
        return indexInFirstHalf != -1 || indexInSecondHalf != -1;
    }

    // ~ log(N)
    private static int maxRank(int[] a, int lo, int hi) {
        int mid = (hi + lo) / 2;
        if (a[mid - 1] > a[mid])
            return maxRank(a, lo, mid - 1);
        else if (a[mid + 1] > a[mid])
            return maxRank(a, mid + 1, hi);
        else
            return mid;
    }


    public static void main(String[] args) {
        int[] bitonicArray = {1, 2, 3, 4, 5, 6, 15, 14, 13, 12};
        int maxRank = maxRank(bitonicArray, 0, bitonicArray.length - 1);
        if (maxRank != 6)
            throw new RuntimeException();
        if (!contains(bitonicArray, 6))
            throw new RuntimeException();
        if (contains(bitonicArray, 7))
            throw new RuntimeException();
        if (!contains(bitonicArray, 12))
            throw new RuntimeException();
        if (!contains(bitonicArray, 1))
            throw new RuntimeException();
        if (!contains(bitonicArray, 15))
            throw new RuntimeException();
        if (contains(bitonicArray, 51))
            throw new RuntimeException();
    }
}
