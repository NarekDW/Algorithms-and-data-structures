package _2_Sorting._2_1_Elementary_Sorts.exercises;

import common.StdRandom;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.isSorted;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.show;

/*****************************************************************************************************
 *
 * From Site: https://algs4.cs.princeton.edu/21elementary/
 * 9. Bogosort.
 * Bogosort is a randomized algorithm that works by throwing the N cards up in the air,
 * collecting them, and checking whether they wound up in increasing order.
 * If they didn't, repeat until they do. Implement bogosort using the shuffling algorithm from
 * Section 1.4. Estimate the running time as a function of N.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class BogoSort {

    // To find the right combination we need N! tries.
    public static void sort(Comparable[] a) {
        while (!isSorted(a))
            StdRandom.shuffle(a);
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
