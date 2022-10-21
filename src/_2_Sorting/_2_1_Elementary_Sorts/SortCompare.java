package _2_Sorting._2_1_Elementary_Sorts;

import common.Stopwatch;
import common.StdOut;
import common.StdRandom;

public class SortCompare {

    public static double time(String alg, Comparable<?>[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
//        if (alg.equals("Merge"))
//        if (alg.equals("Quick"))
//        if (alg.equals("Heap"))

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int n, int T) {  // Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[n];
        for (int t = 0; t < T; t++) {  // Perform one experiment (generate and sort an array).
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static double timeInput(String alg, int T, Double[] a) {
        double total = 0.0;
        for (int t = 0; t < T; t++)
            total += time(alg, a);

        return total;
    }


    /**
     * For 10000 random Doubles
     * Shell is 70.1 times faster than Insertion
     * Shell is 45.1 times faster than Selection
     * <p>
     * For 10000 random Doubles
     * Selection is 1.7 times faster than Insertion
     */
    public static void main(String[] args) {
//        String alg1 = "Shell";
        String alg1 = "Selection";
        String alg2 = "Insertion";
        int N = 10_000;
        int T = 1000;
        double t1 = timeRandomInput(alg1, N, T); // total for alg1
        System.out.println(alg1 + " time: " + t1 + " sec.");

        double t2 = timeRandomInput(alg2, N, T); // total for alg2
        System.out.println(alg2 + " time: " + t2 + " sec.");

        StdOut.printf("For %d random Doubles\n    %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
