package _2_Sorting._2_3_Quicksort.creative;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.StdRandom;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.*;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 *
 * 2.3.18 Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as de-
 * scribed in the text (see page 296). Run doubling tests to determine the effectiveness of
 * the change.
 *
 *
 * run("MedianOf3Partitioning", "Quick", 10_000, 10_000, false);
 * run("MedianOf3Partitioning", "Quick", 10_000, 10_000, false);
 * run("Quick", "MedianOf3Partitioning", 10_000, 10_000, false);
 *
 * 1)
 * MedianOf3Partitioning : 15.149999999999864
 * Quick : 14.172999999999535
 * For 10000 random Doubles MedianOf3Partitioning is 0.936 times faster than Quick
 *
 * 2)
 * MedianOf3Partitioning : 14.394999999999873
 * Quick : 13.062999999999505
 * For 10000 random Doubles MedianOf3Partitioning is 0.907 times faster than Quick
 *
 * 3)
 * Quick : 13.376999999999768
 * MedianOf3Partitioning : 14.56000000000008
 * For 10000 random Doubles Quick is 1.088 times faster than MedianOf3Partitioning
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class MedianOf3Partitioning {

    private static final int M = 10;

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi);
            return;
        }

        moveMedianElementToTheLoIndex(a, lo, hi);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
            }
            while (less(v, a[--j])) {
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    // Move median element of 3 to the lo position
    private static void moveMedianElementToTheLoIndex(Comparable[] a, int lo, int hi) {
        if (hi - lo + 1 > 2) {
            Insertion.sort(a, lo, lo + 2);
            exch(a, lo, lo + 1);
            exch(a, lo + 2, hi);
        }
    }

    // TODO: 22.01.19 Fix This
//    // Move median element of 3 to the lo position
//    private static void moveMedianElementToTheLoIndex2(Comparable[] a, int lo, int hi) {
//        if (hi - lo + 1 > 2) {
//            int xx = lo;
//            int yy = lo + 1;
//            int zz = lo + 2;
//
//            Comparable x = a[lo];
//            Comparable y = a[lo + 1];
//            Comparable z = a[lo + 2];
//
//            if (less(y, x)) {
//                Comparable t = y;
//                y = x;
//                x = t;
//
//                int tt = yy;
//                yy = xx;
//                xx = tt;
//            }
//            if (less(z, y)) {
//                Comparable t = z;
//                z = y;
//                y = t;
//
//                int tt = zz;
//                zz = yy;
//                yy = tt;
//            }
//            if (less(y, x)) {
//                Comparable t = y;
//                y = x;
//                x = t;
//
//                int tt = yy;
//                yy = xx;
//                xx = tt;
//            }
//
////            System.out.println("midIndex: " + yy);
////            System.out.println("maxIndex: " + zz);
//
//            if (less(a[zz], a[yy])){
//                System.out.println("\n");
//                System.out.println(x + " " + y + " " + z);
//                System.out.println("\n");
//                System.out.println(xx + " " + yy + " " + zz);
//            }
//
//            exch(a, lo, yy);
//            exch(a, hi, zz);
//        }
//    }


    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            int s = 1_000;
            Integer[] x = new Integer[s];
            for (int j = 0; j < s; j++)
                x[j] = StdRandom.uniform(s);

            sort(x);

            if (!isSorted(x)) {
                show(x);
                throw new RuntimeException();
            }

        }
    }
}
