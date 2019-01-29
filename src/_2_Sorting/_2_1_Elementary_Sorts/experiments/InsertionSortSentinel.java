package _2_Sorting._2_1_Elementary_Sorts.experiments;

import _2_Sorting._2_1_Elementary_Sorts.SortUtils;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

public class InsertionSortSentinel {

    public static void sort(Comparable[] a) {
        int min = 0;
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (less(a[i], a[min]))
                min = i;
        exch(a, 0, min);

        for (int i = 2; i < n; i++)
            for (int j = i; less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        sort(x);
        SortUtils.show(x);
    }
}
