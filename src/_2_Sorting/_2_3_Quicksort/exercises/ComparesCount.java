package _2_Sorting._2_3_Quicksort.exercises;

import common.SortUtils;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 * <p>
 * 2.3.6 Write a program to compute the exact value of C N , and compare the exact value
 * with the approximation 2N ln N, for N = 100, 1,000, and 10,000.
 *
 ****************************************************************************************************/
public class ComparesCount {
    private static int compares = 0;

    public static <T extends Comparable<T>> void sort(T[] a) {
        compares = 0;
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        T v = a[lo];
        while (true) {
            do {
                compares++;
                if (i == hi) break;
            } while (less(a[++i], v));

            do {
                compares++;
                if (j == lo) break;
            } while (less(v, a[--j]));

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        for (int i = 100; i < 100_000; i *= 10) {
            Double[] doubles = SortUtils.generateArrayDouble(i, false);
            sort(doubles);
            System.out.println(i + ": " + compares + " : " + (int) (2 * i * Math.log(i) / Math.log(2)));
        }
    }
}
