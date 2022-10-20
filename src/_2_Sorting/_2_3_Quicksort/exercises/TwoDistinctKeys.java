package _2_Sorting._2_3_Quicksort.exercises;

import static common.SortUtils.exch;
import static common.SortUtils.show;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 *
 * 2.3.5 Give a code fragment that sorts an array that is known to consist of items having
 * just two distinct keys.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class TwoDistinctKeys {

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        int i = 1;
        while (i <= hi) {
            int cmp = a[i].compareTo(a[lo]);
            if (cmp < 0) exch(a, lo++, i++);
            else if (cmp > 0) exch(a, i, hi--);
            else i++;
        }
    }

    public static void main(String[] args) {
        Integer[] x = {2, 1, 2, 1, 2, 2, 2, 1, 1, 1, 2};
        sort(x);
        show(x);
    }

}
