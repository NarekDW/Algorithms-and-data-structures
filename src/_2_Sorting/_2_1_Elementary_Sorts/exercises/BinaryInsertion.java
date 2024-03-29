package _2_Sorting._2_1_Elementary_Sorts.exercises;

import common.StdRandom;

import static common.SortUtils.isSorted;
import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * From Site: <a href="https://algs4.cs.princeton.edu/21elementary/">...</a>
 * 14. Binary insertion sort.
 * Develop an implementation BinaryInsertion.java of insertion sort that uses
 * binary search to find the insertion point j for entry a[i] and then shifts all of the entries
 * a[j] to a[i-1] over one position to the right. The number of compares to sort an array of length n
 * should be ~ n lg n in the worst case.
 * <p>
 * Note that the number of array accesses will still be quadratic in the worst case.
 * Use SortCompare.java to evaluate the effectiveness of doing so.
 *
 ****************************************************************************************************/
public class BinaryInsertion {

    public static <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            T key = a[i];
            int index = rank(a, key, 0, i);
            for (int j = i; j > index; j--)
                a[j] = a[j - 1];
            a[index] = key;
        }
    }

    private static <T extends Comparable<T>> int rank(T[] a, T key, int lo, int hi) {
        if (lo >= hi) return lo;
        int mid = lo + (hi - lo) / 2;
        if (less(key, a[mid])) return rank(a, key, lo, mid);
        else return rank(a, key, mid + 1, hi);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int amount = 100;
            Integer[] x = new Integer[amount];
            for (int j = 0; j < amount; j++)
                x[j] = amount - j;
            StdRandom.shuffle(x);

            sort(x);
            System.out.println(isSorted(x));
        }
    }
}
