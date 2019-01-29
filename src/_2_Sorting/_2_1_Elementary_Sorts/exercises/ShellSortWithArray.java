package _2_Sorting._2_1_Elementary_Sorts.exercises;

import _2_Sorting._2_1_Elementary_Sorts.SortUtils;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

/*****************************************************************************************************
 *
 * 2.1.11 Implement a version of shellsort that keeps the increment sequence in an array,
 * rather than computing it.
 *
 ****************************************************************************************************/
public class ShellSortWithArray {
    public static void sort(Comparable[] a) {
        int n = a.length;
        /*
            size definition:
            as 3*x < 3*x + 1 =>> size(3x) >= size(3x + 1)
            which means, that we can use 'geometric progression' formula
            to calculate approximate array size
            size = log(3, n) + 1
         */
        int size = (int) (Math.log(n) / Math.log(3) + 1);
        int[] hs = new int[size];
        for (int i = 0, h = 1; h < n; i++, h = 3 * h + 1)
            hs[i] = h;

        for (int i = 0; i < hs.length && hs[i] != 0; i++)
            for (int j = hs[i]; j < n; j++)
                for (int k = j; k >= hs[i] && less(a[k], a[k - hs[i]]); k -= hs[i])
                    exch(a, k, k - hs[i]);
    }

    public static void main(String[] args) {
        int size = 10_000;
        Integer[] x = new Integer[size];
        for (int i = size; i > 0; i--) {
            x[size - i] = i;
        }

        ShellSortWithArray.sort(x);
        SortUtils.show(x);
    }

}
