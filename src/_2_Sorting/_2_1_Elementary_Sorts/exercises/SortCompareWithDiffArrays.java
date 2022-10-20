package _2_Sorting._2_1_Elementary_Sorts.exercises;

import common.StdOut;

import static _2_Sorting._2_1_Elementary_Sorts.SortCompare.timeInput;

/*****************************************************************************************************
 * <p>
 * 2.1.6 Which method runs faster for an array with all keys identical, selection sort or insertion sort?
 * <p>
 * 2.1.7 Which method runs faster for an array in reverse order, selection sort or insertion sort?
 *
 ****************************************************************************************************/
public class SortCompareWithDiffArrays {

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        compareOnIdenticalElements(alg1, alg2);
        compareOnReversedElements(alg1, alg2);
    }

    /**
     * Insertion time: 0.014000000000000002 sec.
     * Selection time: 101.86999999999999 sec.
     * For 100000 identical Doubles
     * Insertion is 7276.4 times faster than Selection
     */
    private static void compareOnIdenticalElements(String alg1, String alg2) {
        int n = 100_000;
        int t = 10;

        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = 1.0;

        double t1 = timeInput(alg1, t, a);
        System.out.println(alg1 + " time: " + t1 + " sec."); // 0.016

        double t2 = timeInput(alg2, t, a);
        System.out.println(alg2 + " time: " + t2 + " sec."); // 103.908

        StdOut.printf("For %d identical Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }

    /**
     * Insertion time: 31.306000000000008 sec.
     * Selection time: 63.306 sec.
     * For 100000 identical Doubles
     * Insertion is 2.0 times faster than Selection
     */
    private static void compareOnReversedElements(String alg1, String alg2) {
        int n = 100_000;
        int t = 10;

        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = (double) (n - i);

        double t1 = timeInput(alg1, t, a);
        System.out.println(alg1 + " time: " + t1 + " sec.");

        double t2 = timeInput(alg2, t, a);
        System.out.println(alg2 + " time: " + t2 + " sec.");

        StdOut.printf("For %d reversed Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }

}
