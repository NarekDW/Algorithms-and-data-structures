package _2_Sorting._2_2_Mergesort.exercises;

import java.util.List;
import java.util.function.Function;

import static _2_Sorting._2_2_Mergesort.exercises.CountMerge.plot;
import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.2.8 Suppose that Algorithm 2.4 is modified to skip the call on merge() whenever a[mid] <= a[mid+1].
 * Prove that the number of compares used to merge sort a sorted array is linear.
 ****************************************************************************************************/
@SuppressWarnings("rawtypes")
public class LinearCompares {
    private Comparable[] aux;
    private int comparesCount = 0;

    public void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, n - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        comparesCount++;
        if (less(a[mid + 1], a[mid]))
            merge(a, lo, mid, hi);
    }

    public void merge(Comparable[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++)
            if (j > hi) {
                a[k] = aux[i++];
            } else if (i > mid) {
                a[k] = aux[j++];
            } else if (less(aux[j], aux[i])) {
                comparesCount++;
                a[k] = aux[j++];
            } else {
                comparesCount++;
                a[k] = aux[i++];
            }
    }

    public static void main(String[] args) {
        Integer[] counts = new Integer[512];
        for (int i = 1; i <= 512; i++) {
            Double[] x = new Double[i];
            for (int k = 0; k < x.length; k++)
                x[k] = (double) k;

            LinearCompares merge = new LinearCompares();
            merge.sort(x);
            counts[i - 1] = merge.comparesCount;
            System.out.println(merge.comparesCount);
        }

        Function<Integer, Double> theoreticalFunction = Double::valueOf;
        List<Function<Integer, Double>> singleTheoreticalFunction = List.of(theoreticalFunction);

        plot(counts, singleTheoreticalFunction);
    }
}
