package _2_Sorting;

/******************************************************************************
 *  Compilation:  javac SortCompare.java
 *  Execution:    java SortCompare alg1 alg2 n trials
 *  Dependencies: StdOut.java Stopwatch.java
 *
 *  Sort n random real numbers, trials times using the two
 *  algorithms specified on the command line.
 *
 *  % java SortCompare Insertion Selection 1000 100
 *  For 1000 random Doubles
 *    Insertion is 1.7 times faster than Selection
 *
 *  Note: this program is designed to compare two sorting algorithms with
 *  roughly the same order of growth, e,g., insertion sort vs. selection
 *  sort or mergesort vs. quicksort. Otherwise, various system effects
 *  (such as just-in-time compiliation) may have a significant effect.
 *  One alternative is to execute with "java -Xint", which forces the JVM
 *  to use interpreted execution mode only.
 *
 ******************************************************************************/

import _2_Sorting._2_2_Mergesort.creative.NaturalMergesort;
import common.Stopwatch;
import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import _2_Sorting._2_1_Elementary_Sorts.Selection;
import _2_Sorting._2_1_Elementary_Sorts.Shell;
import _2_Sorting._2_1_Elementary_Sorts.exercises.BinaryInsertion;
import _2_Sorting._2_1_Elementary_Sorts.exercises.BubbleSort;
import _2_Sorting._2_1_Elementary_Sorts.experiments.InsertionX;
import _2_Sorting._2_2_Mergesort.Merge;
import _2_Sorting._2_2_Mergesort.MergeBU;
import _2_Sorting._2_2_Mergesort.MergeCount;
import _2_Sorting._2_2_Mergesort.MergeSlow;
import _2_Sorting._2_2_Mergesort.creative.FasterMerge;
import _2_Sorting._2_2_Mergesort.creative.MergeX;
import _2_Sorting._2_3_Quicksort.Quick;
import _2_Sorting._2_3_Quicksort.Quick3way;
import _2_Sorting._2_3_Quicksort.creative.MedianOf3Partitioning;
import _2_Sorting._2_3_Quicksort.creative.NonrecursiveQuicksort;
import _2_Sorting._2_3_Quicksort.creative.Sentinels;
import _2_Sorting._2_3_Quicksort.experiments.IgnoreSmallSubarrays;
import _2_Sorting._2_3_Quicksort.experiments.QuickX;
import _2_Sorting._2_4_Priority_Queues.Heap;
import common.StdDraw;
import common.StdOut;
import common.StdRandom;

import java.util.Arrays;

public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch sw = new Stopwatch();
        switch (alg) {
            case "Insertion" -> Insertion.sort(a);
            case "InsertionX" -> InsertionX.sort(a);
            case "BinaryInsertion" -> BinaryInsertion.sort(a);
            case "Selection" -> Selection.sort(a);
            case "Bubble" -> BubbleSort.sort(a);
            case "Shell" -> Shell.sort(a);
            case "Merge" -> Merge.sort(a);
            case "MergeCount" -> MergeCount.sort(a);
            case "MergeSlow" -> MergeSlow.sort(a);
            case "MergeX" -> MergeX.sort(a);
            case "MergeBU" -> MergeBU.sort(a);
            case "NaturalMergesort" -> NaturalMergesort.sort(a);
            case "FasterMerge" -> FasterMerge.sort(a);
            case "Quick" -> Quick.sort(a);
            case "Quick3way" -> Quick3way.sort(a);
            case "QuickSentinels" -> Sentinels.sort(a);
            case "MedianOf3Partitioning" -> MedianOf3Partitioning.sort(a);
            case "NonrecursiveQuicksort" -> NonrecursiveQuicksort.sort(a);
            case "IgnoreSmallSubarrays" -> IgnoreSmallSubarrays.sort(a);
            case "QuickX" -> QuickX.sort(a);
            case "Heap" -> Heap.sort(a);

        /*
                Java’s systems programmers have chosen to use quicksort (with 3-way partitioning)
        to implement the primitive-type methods, and mergesort for reference-type methods.
        The primary practical implications of these choices are, as just discussed, to trade speed
        and memory usage (for primitive types) for stability (for reference types).

        From JDK 1.7 Arrays.sort - uses TimSort!
         */
            case "System" -> Arrays.sort(a);
            default -> throw new IllegalArgumentException("Invalid algorithm: " + alg);
        }
        return sw.elapsedTime();
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeRandomInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        /*
        ------------------------------------------------------------------------------------------------------------
        Warm up JVM is ended
        Selection : 988.2799999999727
        Insertion : 667.6089999999629
        For 10000 random Doubles
            Selection is 0.676 times faster than Insertion
        Warm up JVM is ended


        Selection : 644.8799999999917
        Insertion : 449.5259999999988
        For 10000 random Doubles
            Selection is 0.697 times faster than Insertion
        Warm up JVM is ended
        Insertion : 450.1929999999954
        Selection : 644.4919999999898
        For 10000 random Doubles
            Insertion is 1.432 times faster than Selection


         */
        // Just to warm JVM
//        run("Selection", "Insertion", 10_000, 10_000, false);
        // 1 Selection vs Insertion
//        run("Selection", "Insertion", 10_000, 10_000, false);
        // 2 Selection vs Insertion
//        run("Insertion", "Selection", 10_000, 10_000, false);
//        run("Insertion", "Selection", 10_000, 100, true);

        /*
        ------------------------------------------------------------------------------------------------------------
        Insertion : 688.6939999999506
        Shell : 17.1440000000002
        For 10000 random Doubles Insertion is 0.025 times faster than Shell

        Insertion : 446.48799999999267
        Shell : 14.96200000000014
        For 10000 random Doubles Insertion is 0.034 times faster than Shell

        Shell : 14.908000000000124
        Insertion : 443.6599999999948
        For 10000 random Doubles Shell is 29.760 times faster than Insertion

         */
//        run("Insertion", "Shell", 10_000, 10_000, false);
//        run("Insertion", "Shell", 10_000, 10_000, false);
//        run("Shell", "Insertion", 10_000, 10_000, false);


        /*
        ------------------------------------------------------------------------------------------------------------
        Insertion vs System
        Insertion : 670.5469999999532
        System : 15.320000000000132
        For 10000 random Doubles Insertion is 0.023 times faster than System

        Insertion : 449.36099999999686
        System : 15.025000000000112
        For 10000 random Doubles Insertion is 0.033 times faster than System

        System : 15.004000000000115
        Insertion : 446.9449999999941
        For 10000 random Doubles System is 29.788 times faster than Insertion
         */
//        run("Insertion", "System", 10_000, 10_000, false);
//        run("Insertion", "System", 10_000, 10_000, false);
//        run("System", "Insertion", 10_000, 10_000, false);


        /*
        ------------------------------------------------------------------------------------------------------------
        Insertion : 662.2019999999785
        InsertionSortSentinel : 951.7860000000462
        For 10000 random Doubles Insertion is 1.437 times faster than InsertionSortSentinel

        Insertion : 443.8089999999922
        InsertionSortSentinel : 951.8000000000478
        For 10000 random Doubles Insertion is 2.145 times faster than InsertionSortSentinel

        InsertionSortSentinel : 950.7910000000262
        Insertion : 442.6569999999974
        For 10000 random Doubles InsertionSortSentinel is 0.466 times faster than Insertion
         */
//        run("Insertion", "InsertionSortSentinel", 10_000, 10_000, false);
//        run("Insertion", "InsertionSortSentinel", 10_000, 10_000, false);
//        run("InsertionSortSentinel", "Insertion", 10_000, 10_000, false);

        /*
        ------------------------------------------------------------------------------------------------------------
        Insertion : 465.79800000000523
        InsertionX : 702.0410000000029
        For 10000 random Doubles Insertion is 1.507 times faster than InsertionX

        InsertionX : 702.215000000004
        Insertion : 461.7029999999977
        For 10000 random Doubles InsertionX is 0.657 times faster than Insertion
         */
//        run("Insertion", "InsertionX", 10_000, 10_000, false);
//        run("InsertionX", "Insertion", 10_000, 10_000, false);

//        for (int i =128;;i *= 2) {
//            System.out.println("n = " + i);
//            double timeSelection = timeRandomInput("Selection", i, 5);
//            double timeInsertion = timeRandomInput("Insertion", i, 5);
//            double timeShell = timeRandomInput("Shell", i, 5);
//            System.out.println("timeSelection = " + timeSelection);
//            System.out.println("timeInsertion = " + timeInsertion);
//            System.out.println("timeShell = " + timeShell);
//            System.out.println("\n");
//        }


        /*
        ------------------------------------------------------------------------------------------------------------
        Insertion : 652.4439999999911
        BinaryInsertion : 402.7449999999775
        For 10000 random Doubles Insertion is 0.617 times faster than BinaryInsertion

        Insertion : 437.5010000000097
        BinaryInsertion : 404.35099999999085
        For 10000 random Doubles Insertion is 0.924 times faster than BinaryInsertion

        BinaryInsertion : 416.3249999999841
        Insertion : 473.4570000000033
        For 10000 random Doubles BinaryInsertion is 1.137 times faster than Insertion
         */
//        run("Insertion", "BinaryInsertion", 10_000, 10_000, false);
//        run("Insertion", "BinaryInsertion", 10_000, 10_000, false);
//        run("BinaryInsertion", "Insertion", 10_000, 10_000, false);


        /*
        ------------------------------------------------------------------------------------------------------------
        MergeBU : 17.059000000000673
        Merge : 15.955000000000641
        For 10000 random Doubles MergeBU is 0.935 times faster than Merge

        MergeBU : 16.732000000000742
        Merge : 15.918000000000594
        For 10000 random Doubles MergeBU is 0.951 times faster than Merge

        Merge : 15.899000000000596
        MergeBU : 16.747000000000796
        For 10000 random Doubles Merge is 1.053 times faster than MergeBU
         */
//        run("MergeBU", "Merge", 10_000, 10_000, false);
//        run("MergeBU", "Merge", 10_000, 10_000, false);
//        run("Merge", "MergeBU", 10_000, 10_000, false);



        /*
        ------------------------------------------------------------------------------------------------------------
        Merge : 233.47099999998363
        Shell : 343.6639999999849
        For 100000 random Doubles Merge is 1.472 times faster than Shell

        Merge : 230.68299999997868
        Shell : 271.82699999998226
        For 100000 random Doubles Merge is 1.178 times faster than Shell

        Shell : 271.6839999999817
        Merge : 229.91999999997833
        For 100000 random Doubles Shell is 0.846 times faster than Merge
         */
//        run("Merge", "Shell", 100_000, 10_000, false);
//        run("Merge", "Shell", 100_000, 10_000, false);
//        run("Shell", "Merge", 100_000, 10_000, false);


        /*
        Merge : 17.240000000000666
        MergeX : 13.685999999999638
        For 10000 random Doubles Merge is 0.794 times faster than MergeX

        Merge : 16.8840000000008
        MergeX : 13.653999999999696
        For 10000 random Doubles Merge is 0.809 times faster than MergeX

        MergeX : 13.660999999999676
        Merge : 16.848000000000756
        For 10000 random Doubles MergeX is 1.233 times faster than Merge

        Merge : 16.9160000000008
        MergeBU : 16.59900000000072
        For 10000 random Doubles Merge is 0.981 times faster than MergeBU

        MergeX : 13.671999999999661
        MergeBU : 16.604000000000806
        For 10000 random Doubles MergeX is 1.214 times faster than MergeBU

        Merge : 15.648000000000483
        FasterMerge : 15.244000000000248
        For 10000 random Doubles Merge is 0.974 times faster than FasterMerge
         */
//        run("Merge", "MergeX", 10_000, 10_000, false);
//        run("Merge", "MergeX", 10_000, 10_000, false);
//        run("MergeX", "Merge", 10_000, 10_000, false);
//        run("Merge", "MergeBU", 10_000, 10_000, false);
//        run("MergeX", "MergeBU", 10_000, 10_000, false);
//        run("Merge", "FasterMerge", 10_000, 10_000, false);



        /*
        CUTT_OFF: 3
        For 10000 random Doubles MergeX is time is 14.311

        CUTT_OFF: 5
        For 10000 random Doubles MergeX is time is 14.037

        CUTT_OFF: 10
        For 10000 random Doubles MergeX is time is 13.469

        CUTT_OFF: 15
        For 10000 random Doubles MergeX is time is 13.464

        CUTT_OFF: 20
        For 10000 random Doubles MergeX is time is 14.099

        CUTT_OFF: 30
        For 10000 random Doubles MergeX is time is 14.041

        CUTT_OFF: 40
        For 10000 random Doubles MergeX is time is 14.699
         */
//        // -------------------------------------------------------

        // 2.2.24
//        plotSortedCount();

        // 2.2.26
//        run("Merge", "MergeSlow", 10_000, 10_000, false);
//        run("Merge", "MergeSlow", 10_000, 10_000, false);
//        run("MergeSlow", "Merge", 10_000, 10_000, false);

        // 2.2.27
//        run("MergeCount", 100_000, 1);
//        System.out.println("Cnt:");
//        Integer sum = MergeCount.half.stream().reduce((a, b) -> a + b).get();
//        System.out.println(sum / (MergeCount.half.size() * 1.0));

        // 2.2.28
//        run("Merge", "MergeBU", 10_000, 10_000, false);
//        run("Merge", "MergeBU", 1000, 10_000, false);
//        run("Merge", "MergeBU", 1_000_000, 1, false);
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space (8g last run)
//        run("Merge", "MergeBU", 1_000_000_000, 1, false);


        /*
        Merge : 18.145000000000692
        Quick : 14.857000000000292
        For 10000 random Doubles Merge is 0.819 times faster than Quick

        Merge : 18.143000000000647
        Quick : 15.109000000000133
        For 10000 random Doubles Merge is 0.833 times faster than Quick

        Quick : 15.092000000000175
        System : 17.96400000000063
        For 10000 random Doubles Quick is 1.190 times faster than System
         */
//        run("Merge", "Quick", 10_000, 10_000, false);
//        run("Merge", "Quick", 10_000, 10_000, false);
//        run("Quick", "System", 10_000, 10_000, false);


//        run("QuickSentinels", "Quick", 100_000, 1000, false);
//        run("QuickSentinels", "Quick", 100_000, 1000, false);
//        run("Quick", "QuickSentinels", 100_000, 1000, false);


//        run("MedianOf3Partitioning", "Quick", 10_000, 10_000, false);
//        run("MedianOf3Partitioning", "Quick", 10_000, 10_000, false);
//        run("Quick", "MedianOf3Partitioning", 10_000, 10_000, false);

        // INFINITE
//        run("NonrecursiveQuicksort", "Quick", 10_000, 10_000, false);
//        run("NonrecursiveQuicksort", "Quick", 10_000, 10_000, false);
//        run("Quick", "NonrecursiveQuicksort", 10_000, 10_000, false);


//        run("Quick", "QuickX", 10_000, 10_000, false);
//        run("Quick", "QuickX", 10_000, 10_000, false);
//        run("QuickX", "Quick", 10_000, 10_000, false);


        /*
        Heap : 19.746000000000095
        Quick : 15.753000000000114
        For 10000 random Doubles Heap is 0.798 times faster than Quick

        Heap : 18.6900000000005
        Quick : 14.653999999999991
        For 10000 random Doubles Heap is 0.784 times faster than Quick

        Quick : 14.870000000000116
        Heap : 18.772000000000407
        For 10000 random Doubles Quick is 1.262 times faster than Heap
         */
//        run("Heap", "Quick", 10_000, 10_000, false);
//        run("Heap", "Quick", 10_000, 10_000, false);
//        run("Quick", "Heap", 10_000, 10_000, false);

    }

    public static double run(String alg1, String alg2, int n, int trials, boolean sortedInput) {
        double time1, time2;
        if (sortedInput) {
            time1 = timeSortedInput(alg1, n, trials);   // Total for alg1.
            time2 = timeSortedInput(alg2, n, trials);   // Total for alg2.
        } else {
            time1 = timeRandomInput(alg1, n, trials);   // Total for alg1.
            System.out.println(alg1 + " : " + time1);
            time2 = timeRandomInput(alg2, n, trials);   // Total for alg2.
            System.out.println(alg2 + " : " + time2);
        }

        StdOut.printf("For %d random Doubles %s is", n, alg1);
        double comp = time2 / time1;
        StdOut.printf(" %.3f times faster than %s\n", comp, alg2);
        return comp;
    }

    public static void run(String alg, int n, int trials, boolean displayTime) {
        double time1 = timeRandomInput(alg, n, trials);   // Total for alg1.
        if (displayTime) {
            StdOut.printf("For %d random Doubles\n    %s is ", n, alg);
            StdOut.printf("time is %.3f  \n", time1);
        }
    }
}
