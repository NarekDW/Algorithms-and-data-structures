package _2_Sorting._2_1_Elementary_Sorts.exercises;

import common.StdRandom;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.*;

public class BubbleSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = n; i > 0; i--)
            for (int j = 1; j < i; j++)
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int amount = 100;
            Integer[] x = new Integer[amount];
            for (int j = 0; j < amount; j++)
                x[j] = amount - j;
            StdRandom.shuffle(x);
            sort(x);
            if (!isSorted(x))
                System.out.println("Not Sorted");
        }
    }
}
