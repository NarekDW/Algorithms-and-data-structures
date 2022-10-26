package _2_Sorting._2_2_Mergesort.exercises;

import common.StdDraw;
import common.StdRandom;

import java.awt.*;

import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.2.6 Write a program to compute the exact value of the number of array accesses used by
 * top-down mergesort and by bottom-up mergesort.
 * Use your program to plot the values for N from 1 to 512, and to compare the exact values
 * with the upper bound 6N*lg(N).
 *
 ****************************************************************************************************/
@SuppressWarnings("rawtypes")
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
                count += 2;
            } else if (i > mid) {
                a[k] = aux[j++];
                count += 2;
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += 4;
            } else {
                a[k] = aux[i++];
                count += 2;
            }
    }

    public static void plot(Integer[] x, Integer[] y) {
        int xMax = getXAxisMax(x);
        int yMax = getYAxisMax(x, y);

        double x0 = xMax / -5.0;
        double xn = xMax * 1.2;
        StdDraw.setXscale(x0, xn);
        double y0 = yMax / -5.0;
        double yn = yMax * 1.2;
        StdDraw.setYscale(y0, yn);
        StdDraw.line(x0, 0, xn, 0);
        StdDraw.line(0, y0, 0, yn);
        StdDraw.text(x0 * 0.5, yn * 0.9, String.valueOf((int) yMax));
        StdDraw.text(xn * 0.9, y0 * 0.3, String.valueOf((int) xMax));

        for (int i = 1; i < xMax; i++) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(i - 1, x[i - 1], i, x[i]);
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(i - 1, y[i - 1], i, y[i]);

            StdDraw.setPenColor(Color.GREEN);
            StdDraw.line(i - 1, (6 * i * Math.log(i) / Math.log(2)), i, (6 * (i + 1) * Math.log(i + 1) / Math.log(2)));
        }
    }

    private static Integer getXAxisMax(Integer[] arr) {
        return arr.length;
    }

    private static Integer getYAxisMax(Integer[] arr1, Integer[] arr2) {
        Integer max = arr1[0];
        for (int i = 1; i < arr1.length; i++)
            if (arr1[i] > max)
                max = arr1[i];

        for (Integer integer : arr2)
            if (integer > max)
                max = integer;

        return max;
    }


    public static void main(String[] args) {
        Integer[] counts = new Integer[512];
        Integer[] countsBU = new Integer[512];
        for (int i = 1; i <= 512; i++) {
            Double[] x = new Double[i];
            Double[] y = new Double[i];
            for (int k = 0; k < x.length; k++)
                x[k] = StdRandom.uniform();
            System.arraycopy(x, 0, y, 0, x.length);

            CountMerge merge = new CountMerge();
            merge.sort(x);
            counts[i - 1] = merge.count;

            CountMerge mergeBU = new CountMerge();
            mergeBU.sortBU(y);
            countsBU[i - 1] = mergeBU.count;

            System.out.println(merge.count + " : " + mergeBU.count);
        }
        plot(counts, countsBU);
    }
}
