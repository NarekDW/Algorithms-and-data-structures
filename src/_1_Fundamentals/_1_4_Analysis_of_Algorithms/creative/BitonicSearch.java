package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.BinarySearch;

/*****************************************************************************************************
 *
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
        int maxElemIndex = rank(a, 0, a.length - 1);
        // log(N)
        int indexInFirstHalf = BinarySearch.rank(a, key, 0, maxElemIndex);
        // log(N)
        int indexInSecondHalf = BinarySearch.reversedRank(a, key, maxElemIndex + 1, a.length - 1);
        return indexInFirstHalf != -1 || indexInSecondHalf != -1;
    }

    // ~ log(N)
    private static int rank(int[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid - 1] < a[mid] && a[mid] > a[mid + 1])
            return mid;
        else if (a[mid - 1] > a[mid])
            return rank(a, lo, mid - 1);
        else
            return rank(a, mid + 1, hi);
    }


    public static void main(String[] args) {
        int[] bitonicArray = {1, 2, 3, 4, 5, 6, 15, 14, 13, 12};
        int i = rank(bitonicArray, 0, bitonicArray.length - 1);
        System.out.println("index = " + i + " : elem = " + bitonicArray[i]);

        System.out.println("6 - " + contains(bitonicArray, 6));
        System.out.println("7 - " + contains(bitonicArray, 7));
        System.out.println("12 - " + contains(bitonicArray, 12));
        System.out.println("1 - " + contains(bitonicArray, 1));
        System.out.println("15 - " + contains(bitonicArray, 15));
        System.out.println("51 - " + contains(bitonicArray, 51));
    }
}
