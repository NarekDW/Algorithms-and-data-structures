package _2_Sorting._2_1_Elementary_Sorts.creative;

import common.StdRandom;

import java.util.Arrays;

import static common.SortUtils.exch;

/*****************************************************************************************************
 * <p>
 * 2.1.19 Shellsort worst case.
 * Construct an array of 100 elements containing the numbers 1 through 100 for which shellsort,
 * with the increments 1 4 13 40 , uses as large a number of compares as you can find.
 * <p>
 * 2.1.20 Shellsort best case. What is the best case for shellsort? Justify your answer.
 *
 ****************************************************************************************************/
@SuppressWarnings({"SameParameterValue", "rawtypes", "unchecked"})
public class ShellSortWorstAndBestCases {

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


    public static void main(String[] args) {
        shellSortWorstCase();
        shellSortBestCase();
    }

    private static void shellSortWorstCase() {
        hReversed(1, 100); // 500
        hReversed(40, 100); // 780
        hReversed(13, 100); // 921
        hReversed(4, 100); // 793
        practicalWorstAndBestCases(100);
    }

    private static void shellSortBestCase() {
        sortedArray(100); // 342
        practicalWorstAndBestCases(100);
    }

    private static void hReversed(int h, int size) {
        Integer[] x = new Integer[size];
        int initialValue = size;
        int segments = size / h + 1;
        for (int segmentElement = 0; segmentElement < h; segmentElement++) {
            for (int j = 0; j < segments; j++) {
                int segment = j * h;
                if (segmentElement + segment < size)
                    x[segmentElement + segment] = initialValue--;
            }
        }

        sort(x);
        System.out.println("For h: " + h + " Count: " + count); // 921
    }

    private static void practicalWorstAndBestCases(int size) {
        Integer[] x = new Integer[size];
        for (int i = 0; i < size; i++)
            x[i] = i + 1;

        int max = count;
        int min = size * size;
        Integer[] maxArr = new Integer[size];
        Integer[] minArr = new Integer[size];
        Integer[] tmp = new Integer[size];
        for (int i = 0; i < 10_000_000; i++) {
            StdRandom.shuffle(x);
            System.arraycopy(x, 0, tmp, 0, size);
            sort(x);

            if (count > max) {
                max = count;
                maxArr = tmp;
            }

            if (count < min) {
                min = count;
                minArr = tmp;
            }
            count = 0;
        }

        System.out.println("Max Count: " + max);
        System.out.println(Arrays.toString(maxArr));

        System.out.println("Min Count: " + min);
        System.out.println(Arrays.toString(minArr));
    }

    private static void sortedArray(int size) {
        Integer[] x = new Integer[size];
        for (int i = 0; i < size; i++)
            x[i] = i + 1;

        sort(x);
        System.out.println("Count for sorted array: " + count);
    }
}
