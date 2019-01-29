package _2_Sorting._2_1_Elementary_Sorts.exercises;

import common.StdRandom;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.*;

/*****************************************************************************************************
 *
 * From Site: https://algs4.cs.princeton.edu/21elementary/
 * 8. Guess-sort.
 * Pick two indices i and j at random; if a[i] > a[j], then swap them.
 * Repeat until the input is sorted. Analyze the expected running time of this algorithm.
 * Hint: after each swap, the number of inversions strictly decreases.
 * If there are m bad pairs, then the expected time to find a bad pair is Theta(n^2/m).
 * Summing up from m =1 to n^2 yields O(N^2 log N) overall, ala coupon collector.
 * This bound is tight: consider input 1 0 3 2 5 4 7 6 ...
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class GuessSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        int i = uniform(n);
        int j = uniform(n);
        while (!isSorted(a)) {
            if (less(a[i], a[j]))
                exch(a, i, j);
            i = uniform(n);
            j = uniform(n);
        }
    }

    private static int uniform(int n) {
        return StdRandom.uniform(n);
    }

    public static void main(String[] args) {
        int amount = 11;
        Integer[] x = new Integer[amount];
        for (int i = 0; i < amount; i++)
            x[i] = amount - i;

        sort(x);
        show(x);
    }
}
