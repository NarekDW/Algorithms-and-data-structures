package _2_Sorting._2_2_Mergesort.exercises;

import common.StdDraw;
import common.StdRandom;

import java.awt.*;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

@SuppressWarnings("Duplicates")
public class CountMerge {

    private Comparable[] aux;

    private int count = 0;

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
        merge(a, lo, mid, hi);
    }

    public void sortBU(Comparable[] a) { // Do lg N passes of pairwise merges.
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) // sz: subarray size
            for (int lo = 0; lo < N - sz; lo += sz + sz) // lo: subarray index
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    }

    public void merge(Comparable[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            count += 2;
        }

        for (int k = lo; k <= hi; k++)
            if (j > hi) {
                a[k] = aux[i++];
                count++;
            } else if (i > mid) {
                a[k] = aux[j++];
                count++;
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += 4;
            } else {
                a[k] = aux[i++];
                count++;
            }
    }

    public static void plot(Integer[] x, Integer[] y) {
        StdDraw.setXscale(0, 550);
        StdDraw.setYscale(0, 30_000);
        for (int i = 1; i < x.length; i++) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(i - 1, x[i - 1], i, x[i]);
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(i - 1, y[i - 1], i, y[i]);

            StdDraw.setPenColor(Color.GREEN);
            StdDraw.line(i - 1, (6 * i * Math.log(i) / Math.log(2)), i, (6 * (i + 1) * Math.log(i + 1) / Math.log(2)));
        }
    }


    public static void main(String[] args) {
        Integer[] counts = new Integer[512];
        Integer[] countsBU = new Integer[512];
        for (int i = 1; i <= 512; i++) {
            Double[] x = new Double[i];
            for (int k = 0; k < x.length; k++) {
                x[k] = StdRandom.uniform();
            }

            CountMerge merge = new CountMerge();
            merge.sort(x);
            counts[i - 1] = merge.count;

            CountMerge mergeBU = new CountMerge();
            mergeBU.sortBU(x);
            countsBU[i - 1] = mergeBU.count;

            System.out.println(merge.count + " : " + mergeBU.count);
        }
        plot(counts, countsBU);
    }
}
