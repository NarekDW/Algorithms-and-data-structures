package _2_Sorting;

import _2_Sorting._2_2_Mergesort.creative.MergeX;
import common.StdDraw;

import java.awt.*;

import static _2_Sorting.SortCompare.run;
import static _2_Sorting.SortCompare.timeRandomInput;

public class MergeCompares {

    /**
     * For 1_000_000 items:
     * <p>
     * MergeX               ~ 18.8 sec.
     * FasterMerge          ~ 28.5
     * Merge                ~ 29.0 sec.
     * MergeBU              ~ 30.7 sec.
     * NaturalMergesort     ~ MergeBU
     */
    public static void main(String[] args) {
        mergeVsMergeSort3Way();
//        plotSortTimes("MergeSort3Way", "Merge");
    }

    private static void mergeVsMergeSort3Way() {
        // For 1000000 random Doubles MergeSort3Way is 1.901 times faster than Merge
        run("MergeSort3Way", "Merge", 30_000_000, 3, false);
    }

    private static void mergeBUvsNaturalMergesort() {
        // For 1000000 random Doubles NaturalMergesort is 0.926 times faster than MergeBU
        // For 1000000 sorted Doubles NaturalMergesort is 124.584 times faster than MergeBU
        run("NaturalMergesort", "MergeBU", 1_000_000, 50, false);
    }

    private static void mergeVsMergeX() {
        // For 1000000 random Doubles MergeX is 1.503 times faster than Merge
        run("MergeX", "Merge", 1_000_000, 50, false);
    }

    private static void mergeVsMergeBU() {
        // For 1000000 random Doubles Merge is 1.131 times faster than MergeBU
        run("Merge", "MergeBU", 1_000_000, 50, false);
    }

    private static void fasterMergeVsMerge() {
        // For 1000000 random Doubles FasterMerge is 1.028 times faster than Merge
        run("FasterMerge", "Merge", 1_000_000, 50, false);
    }

    private static void plotSortedCount() {
        int x = 0;
        int y = 0;
        StdDraw.setCanvasSize(1200, 700);
        StdDraw.setYscale(-50, 350);
        StdDraw.setXscale(-100_000, 2_200_000);

        for (int i = 0; i <= 350; i += 50)
            StdDraw.text(-50_000, i, i + "");

        for (int i = 5_000; i <= 2_000_000; i += 5_000) {
            run("MergeX", i, 1, false);
            int sortedCnt = MergeX.sortedCnt;
            System.out.println(i + " : " + sortedCnt);
            StdDraw.line(x, y, i, sortedCnt);
            x = i;
            y = sortedCnt;

            if (i % 500_000 == 0)
                StdDraw.text(i, -30, String.valueOf(i / 100_000));
        }

        StdDraw.save("./src/resources/sort/merge/AlreadySortedSubArraysCount.png");
    }

    private static void plotSortTimes(String arg1, String arg2) {
        int maxSize = 1_000_000;
        StdDraw.setXscale(-maxSize * 0.1, maxSize * 1.1);
        StdDraw.setYscale(-0.1, 1);

        for (int i = 0; i <= maxSize; i = i + maxSize / 10) {
            StdDraw.text(i, -0.05, String.valueOf(i / 100_000));
        }

        for (int i = 100_000; i < maxSize; i+=5000) {
            StdDraw.setPenColor(Color.GREEN);
            double time1 = timeRandomInput(arg1, i, 1);
            StdDraw.point(i, time1);
            StdDraw.setPenColor(Color.RED);
            double time2 = timeRandomInput(arg2, i, 1);
            StdDraw.point(i, time2);
        }
    }
}
