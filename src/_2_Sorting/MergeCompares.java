package _2_Sorting;

import _2_Sorting._2_2_Mergesort.creative.MergeX;
import common.StdDraw;

import static _2_Sorting.SortCompare.run;

public class MergeCompares {

    /**
     * For 1_000_000 items:
     * <p>
     *     MergeX      ~ 18.8 sec.
     *     FasterMerge ~ 28.5
     *     Merge       ~ 29.0 sec.
     *     MergeBU     ~ 30.7 sec.
     *
     */
    public static void main(String[] args) {
        plotSortedCount();
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

        for (int i = 0; i <= 350; i+=50)
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
}
