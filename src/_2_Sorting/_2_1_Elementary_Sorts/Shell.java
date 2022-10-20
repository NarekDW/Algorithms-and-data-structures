package _2_Sorting._2_1_Elementary_Sorts;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

public class Shell {

    @SuppressWarnings("rawtypes")
    // When h = 1 it becomes to Insertion Sort.
    public static void sort(Comparable[] a) {
        int n = a.length; // 21
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1; // 1 4 13
        while (h >= 1) {
            for (int i = h; i < n; i++)
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 7, 6, 4, 2, 1, 11, 12, 0, 17, 22, 25, 33, 36, 37, 38, 41, 42, 44};
        Shell.sort(x);
        SortUtils.show(x);
    }

}
