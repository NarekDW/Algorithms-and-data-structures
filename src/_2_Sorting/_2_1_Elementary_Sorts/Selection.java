package _2_Sorting._2_1_Elementary_Sorts;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

/*
E A S Y Q U E S T I O N
A E S Y Q U E S T I O N
A E E Y Q U S S T I O N

 */
public class Selection {

    // select min element on each iteration
    // Selection sort uses N^2/2 compares and N exchanges to sort an array of length N.
    public static void sort(Comparable[] a) {
        int n = a.length;
        int min;
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i + 1; j < n; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        Selection.sort(x);
        SortUtils.show(x);
    }

}
