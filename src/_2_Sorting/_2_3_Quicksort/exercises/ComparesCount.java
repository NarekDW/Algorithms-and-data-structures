package _2_Sorting._2_3_Quicksort.exercises;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 *
 * 2.3.6 Write a program to compute the exact value of C N , and compare the exact value
 * with the approximation 2N ln N, for N = 100, 1,000, and 10,000.
 *
 ****************************************************************************************************/
public class ComparesCount {

    private static int CNT = 0;

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
//        System.out.println(lo + " : " + hi);
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                CNT++;
                if (i == hi) break;
            }
            CNT++;

            while (less(v, a[--j])) {
                CNT++;
                if (j == lo) break;
            }
            CNT++;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        int N = 1_000;

        Double[] x = new Double[N];
        for (int i = 0; i < N; i++)
//            x[i] = StdRandom.uniform();
            x[i] = 1.0;

        sort(x);

        // count is from 50 to 70 % from 2NlnN in average
//        System.out.println(CNT + " : " + (2 * N * Math.log(N) / Math.log(Math.E)));
        System.out.println(CNT + " : " + (N * Math.log(N) / Math.log(2)));
    }
}
