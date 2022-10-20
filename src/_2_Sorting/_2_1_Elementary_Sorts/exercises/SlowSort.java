package _2_Sorting._2_1_Elementary_Sorts.exercises;

import common.StdRandom;

import static common.SortUtils.*;

/*****************************************************************************************************
 *
 * 10. Slow sort.
 * Consider the following sorting algorithm: choose two integer i and j at random.
 * If i < j, but a[i] > a[j], swap them. Repeat until the array is in ascending order.
 * Argue that the algorithm will eventually finish (with probability 1).
 * How long will it takes as a function of N? Hint: How many swaps will it make in the worst case?
 *
 ****************************************************************************************************/
public class SlowSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        int i = uniform(n);
        int j = uniform(n);
        while (!isSorted(a)) {
            if (i < j) {
                int t = j;
                j = i;
                i = t;
            }

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
        int amount = 1000;
        Integer[] x = new Integer[amount];
        for (int i = 0; i < amount; i++)
            x[i] = amount - i;

        sort(x);
        show(x);
    }

}
