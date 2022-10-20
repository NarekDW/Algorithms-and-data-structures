package _2_Sorting._2_3_Quicksort.creative;

import static common.SortUtils.*;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 *
 * 2.3.15 Nuts and bolts. (G. J. E. Rawlins) You have a mixed pile of N nuts and N bolts
 * and need to quickly find the corresponding pairs of nuts and bolts. Each nut matches
 * exactly one bolt, and each bolt matches exactly one nut. By fitting a nut and bolt to-
 * gether, you can see which is bigger, but it is not possible to directly compare two nuts or
 * two bolts. Give an efficient method for solving the problem.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class NutsAndBolts {

    public static void sort(Comparable[] nuts, Comparable[] bolts) {
        shuffle(nuts); // ~ N
        shuffle(bolts); // ~ N
        sort(nuts, bolts, 0, nuts.length - 1);
    }

    // ~ 2N * log(N)
    public static void sort(Comparable[] nuts, Comparable[] bolts, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(bolts, nuts[lo], lo, hi);
        int jj = partition(nuts, bolts[j], lo, hi);
        assert j == jj;

        sort(nuts, bolts, lo, j - 1);
        sort(nuts, bolts, j + 1, hi);
    }

    /*
    First solution:
        Move key to the 'lo' position when we face it.
     */
    private static int partition(Comparable[] a, Comparable key, int lo, int hi) {
        int i = lo - 1, j = hi + 1;
        while (true) {
            while (less(a[++i], key)) if (i == hi) break;
            while (less(key, a[--j])) if (j == lo) break;
            if (i >= j) {
                if (i == j && a[lo].compareTo(key) != 0)
                    exch(a, lo, j);
                break;
            }

            exch(a, i, j);

            if (key.compareTo(a[i]) == 0)
                exch(a, lo, i);
            else if (key.compareTo(a[j]) == 0)
                exch(a, lo, j++);
        }

        exch(a, lo, j);
        return j;
    }

    /*
    Second solution:
        In this case we don't move key to the known position,
        but we change 'keyPivot' to the position where key is at the moment.
     */
    private static int partition2(Comparable[] a, Comparable key, int lo, int hi) {
        int i = lo - 1, j = hi + 1, keyPivot = lo;
        while (true) {
            while (less(a[++i], key)) if (i == hi) break;
            while (less(key, a[--j])) if (j == lo) break;
            if (i > j) {
                break;
            } else if (i == j) {
                keyPivot = j;
                break;
            }

            if (key.compareTo(a[i]) == 0)
                keyPivot = j;
            else if (key.compareTo(a[j]) == 0)
                keyPivot = i;

            exch(a, i, j);
        }

        if (keyPivot <= j) {
            exch(a, keyPivot, j);
            return j;
        } else {
            exch(a, keyPivot, i);
            return i;
        }
    }

    // Test
    public static void main(String[] args) {
        for (int j = 0; j < 10_000; j++) {
            int s = 1_000;
            Integer[] nuts = new Integer[s];
            // distinct
            for (int i = 0; i < s; i++)
                nuts[i] = i;

            shuffle(nuts);
            Integer[] bolts = nuts.clone();
            shuffle(bolts);

            sort(nuts, bolts);

            if (!isSorted(nuts) || !isSorted(bolts)) {
                System.out.println("Nuts:");
                show(nuts);
                System.out.println("Bolts:");
                show(bolts);

                throw new RuntimeException();
            }
        }
    }
}
