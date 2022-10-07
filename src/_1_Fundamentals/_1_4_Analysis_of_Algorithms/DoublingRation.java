package _1_Fundamentals._1_4_Analysis_of_Algorithms;

import common.StdRandom;

@SuppressWarnings("ALL")
public class DoublingRation {

    private static final int MAXIMUM_INTEGER = 1_000_000;

    // This class should not be instantiated.
    private DoublingRation() {
    }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     * with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        // Algorithm
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int repeats = 100;
        double prev = 0;
        for (int i = 0; i < 10; i++)
            prev = timeTrial(4000);

        for (int n = 250; true; n += n) {
            double time = 0;
            for (int i = 0; i < repeats; i++) {
                time = timeTrial(n);
                System.out.printf("%6d %7.3f ", n, time);
                System.out.printf("%5.1f\n", time / prev);
            }
            prev = time;
        }
    }

}
