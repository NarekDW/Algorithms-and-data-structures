package _2_Sorting._2_3_Quicksort.experiments;

import _2_Sorting.SortCompare;
import common.StdDraw;
import common.Tuple;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/*****************************************************************************************************
 * <p>
 * 2.3.25 Cutoff to insertion sort. Implement quicksort with a cutoff to insertion sort
 * for subarrays with less than M elements, and empirically determine the value of M for
 * which quicksort runs fastest in your computing environment to sort random arrays
 * of N doubles, for N = 10^3 , 10^4 , 10^5 , and 10^6 . Plot average running times for M from 0
 * to 30 for each value of M. Note : You need to add a three-argument sort() method to
 * Algorithm 2.2 for sorting subarrays such that the call Insertion.sort(a, lo, hi)
 * sorts the subarray a[lo..hi] .
 *
 ****************************************************************************************************/
public class QuickVsQuickX {

    public static void main(String[] args) {
        SortCompare.run("Quick", "QuickX", 10_000, 10_000, false);

        plotSortedCount(1_000, 10_000);
        plotSortedCount(10_000, 1_000);
        plotSortedCount(100_000, 100);
        plotSortedCount(1_000_000, 10);
    }

    private static void plotSortedCount(int n, int trials) {
        StdDraw.setCanvasSize(1748, 1000);
        StdDraw.setYscale(600, 1500);
        StdDraw.setXscale(-2, 55);

        StdDraw.setPenColor(Color.BLUE);
        StdDraw.text(50, 1400, n + "");
        StdDraw.setPenColor(Color.BLACK);

        for (int i = 0; i <= 50; i++)
            StdDraw.text(i, 620, i + "");

        for (int i = 650; i <= 1500; i += 50)
            StdDraw.text(-0.5, i, i + "");

        ArrayList<Tuple<Double>> compares = new ArrayList<>();

        QuickX.M = 0;
        double y = SortCompare.run("QuickX", "Quick", n, trials, false) * 1_000;
        int x = 0;

        compares.add(new Tuple<>((double) x, y));

        for (int i = 1; i <= 50; i += 1) {
            QuickX.M = i;
            double j = SortCompare.run("QuickX", "Quick", n, trials, false) * 1_000;
            compares.add(new Tuple<>((double) i, j));
            StdDraw.line(x, y, i, j);
            x = i;
            y = j;
        }

        Tuple<Double> max = compares.stream().max(Comparator.comparingDouble(Tuple::getY)).get();
        maxLine(max);
    }

    private static void maxLine(Tuple<Double> max) {
        StdDraw.setPenColor(Color.RED);

        for (int i = 0; i <= max.getX() - 1; i += 2)
            StdDraw.line(i, max.getY(), i + 1, max.getY());

        for (int i = 650; i <= max.getY() - 50; i += 100)
            StdDraw.line(max.getX(), i, max.getX(), i + 50);

        StdDraw.setPenColor(Color.BLACK);
    }

}
