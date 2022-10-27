package _2_Sorting;

import static _2_Sorting.SortCompare.run;

public class MergeCompares {

    public static void main(String[] args) {
        mergeVsMergeBU();
    }

    private static void mergeVsMergeBU() {
        // For 1000000 random Doubles Merge is 1.062 times faster than MergeBU
        run("Merge", "MergeBU", 1_000_000, 50, true);
    }

    private static void fasterMergeVsMerge() {
        // For 1000000 random Doubles FasterMerge is 1.028 times faster than Merge
        run("FasterMerge", "Merge", 1_000_000, 100, false);
    }

}
