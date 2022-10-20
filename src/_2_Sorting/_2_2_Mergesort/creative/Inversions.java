package _2_Sorting._2_2_Mergesort.creative;

import static common.SortUtils.less;

/*****************************************************************************************************
 *
 * 2.2.19 Inversions. Develop and implement a linearithmic algorithm for computing
 * the number of inversions in a given array (the number of exchanges that would be
 * performed by insertion sort for that array—see Section 2.1). This quantity is related
 * to the Kendall tau distance; see Section 2.5.
 *
 ****************************************************************************************************/

public class Inversions {

    public static long count(Comparable[] a) {
        Comparable[] clone = a.clone();
        Comparable[] aux = new Comparable[a.length];

        return count(clone, aux, 0, a.length - 1);
    }

    private static long count(Comparable[] a, Comparable[] aux, int lo, int hi) {
        long inversions = 0;
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, aux, lo, mid);
        inversions += count(a, aux, mid + 1, hi);
        inversions += merge(a, aux, lo, mid, hi);

        return inversions;
    }

    private static long merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        long inversions = 0;

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++)
            if (j > hi)                     a[k] = aux[i++];
            else if (i > mid)               a[k] = aux[j++];
            else if (less(aux[j], aux[i])) {a[k] = aux[j++]; inversions += mid - i + 1;} // ???
            else                            a[k] = aux[i++];

        return inversions;
    }


    public static void main(String[] args) {
        Comparable[] x = new Comparable[10];

        for (int i = 0; i < 10; i++)
            x[i] = 10 - i;

        long inversions = count(x);
        System.out.println("inversions: " + inversions);
        System.out.println("inversions: " + brute(x, 0, x.length - 1));
    }

    // count number of inversions in a[lo..hi] via brute force (for debugging only)
    private static <Key extends Comparable<Key>> long brute(Key[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (less(a[j], a[i])) inversions++;
        return inversions;
    }
}
