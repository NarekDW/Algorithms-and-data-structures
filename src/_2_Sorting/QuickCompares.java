package _2_Sorting;

import static _2_Sorting.SortCompare.run;

public class QuickCompares {

    public static void main(String[] args) {
        quickVsMedianOf3Partitioning();
    }

    private static void quickVsMedianOf3Partitioning() {
        // For 1000000 random Doubles MedianOf3Partitioning is 0.899 times faster than Quick
        run("MedianOf3Partitioning", "Quick", 1_000_000, 50, false);
    }

    private static void quickVsQuick3Way() {
        // For 1000000 random Doubles Quick3way is 0.443 times faster than Quick
        run("Quick3way", "Quick", 1_000_000, 50, false);
    }

    private static void quickVsQuickSentinels() {
        // For 1000000 random Doubles QuickSentinels is 0.928 times faster than Quick
        run("QuickSentinels", "Quick", 1_000_000, 50, false);
    }
}
