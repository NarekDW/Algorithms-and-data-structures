package _2_Sorting._2_1_Elementary_Sorts;

import common.SortUtils;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.SortUtils.show;

public class Insertion {

    /*
    on the average:
    ~ N^2/4 compares and N^2/4 exchanges

    on the worst case:
    ~ N^2/2 compares and N^2/2 exchanges

    on best case:
    N - 1 compares and 0 exchanges
     */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    public static void sort(Comparable[] a, int l, int h) {
        for (int i = l + 1; i <= h; i++)
            for (int j = i; j > l && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        Insertion.sort(x);
        SortUtils.show(x);

        Integer[] xx = {3, 1, 2};
        sort(xx, 0, 2);
        System.out.println("XX:");
        show(xx);
    }

}
