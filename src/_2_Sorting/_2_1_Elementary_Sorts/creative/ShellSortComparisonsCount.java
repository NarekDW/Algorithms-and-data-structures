package _2_Sorting._2_1_Elementary_Sorts.creative;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;

/*****************************************************************************************************
 *
 * 2.1.19 Shellsort worst case. Construct an array of 100 elements containing the num-
 * bers 1 through 100 for which shellsort, with the increments 1 4 13 40 , uses as large a
 * number of compares as you can find.
 *
 * 2.1.20 Shellsort best case. What is the best case for shellsort? Justify your answer.
 *
 ****************************************************************************************************/
public class ShellSortComparisonsCount {

    private static int count = 0;

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++)
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            h = h / 3;
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        count++;
        return v.compareTo(w) < 0;
    }

    public static void showX(Integer[] x) {
        for (int i : x) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /****************************************************************************************************
         * The Worst Case
         * **************************************************************************************************/

//        Integer[] x = new Integer[100];
        // head - tail insertion
        // count = 530
//        for (int i = 0; i < 100; i++) {
//            if (i < 50)
//                x[i] = 100 - 2 * i;
//            else
//                x[i] = 2*i - 99;
//        }

        // REVERSED
        // count = 500
//        for (int i = 0; i < 100; i++) {
//            x[i] = 100 - i;
//        }

//        int h = 1;
//        // h recursion
//        while (h < 100 / 3){
//            h = 3 * h + 1;
//            System.out.println(h);
//        }
//
//        for (int i = 0; i < 40; i++) {
//            x[i] = 100 - 2 * i;
//            x[i + h] = 100 - 1 - 2 * i;
//        }
//
//        for (int i = 80; i < 100; i++) {
//
//        }

//        count = 780
        int h = 40;
        int m = 100;
        Integer[] x = new Integer[100];
        for (int i = 0; i < 40; i++) {
            x[i] = m--;
            x[i + h] = m--;
            if (i + 2 * h < 100)
                x[i + 2 * h] = m--;
        }
        showX(x);

        sort(x);
        showX(x);
        System.out.println("count = " + count);


        /****************************************************************************************************
         * The Best Case
         * best count = 342
         * **************************************************************************************************/
//        Integer[] best = new Integer[100];
//        for (int i = 0; i < 100; i++) {
//            best[i] = i + 1;
//        }
//
//        sort(best);
//        showX(best);
//        System.out.println("best count = " + count);

    }
}
