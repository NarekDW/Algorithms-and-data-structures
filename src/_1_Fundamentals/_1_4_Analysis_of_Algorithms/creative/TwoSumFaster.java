package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.StopwatchCPU;
import _1_Fundamentals._1_4_Analysis_of_Algorithms.TwoSumFast;
import common.In;

import java.util.Arrays;
import java.util.function.Function;

/*****************************************************************************************************
 * <p>
 * 1.4.15 Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that
 * uses a linear algorithm to count the pairs that sum to zero after the array is sorted
 * (instead of the binary-search-based linearithmic algorithm).
 * Then apply a similar idea to develop a quadratic algorithm for the 3-sum problem.
 *
 ****************************************************************************************************/
public class TwoSumFaster {

    public static int count(int[] a) {
        int n = a.length;
        if (n > 0 && a[0] > 0)
            return 0;

        int count = 0;
        for (int i = 0, j = n - 1; i < j; ) {
            int left = Math.abs(a[i]);
            int right = Math.abs(a[j]);
            if (left == right) {
                count++;
                i++;
                j--;
            } else if (right > left)
                j--;
            else
                i++;
        }

        return count;
    }

    public static void main(String[] args) {
        /*
         * Data files:   https://algs4.cs.princeton.edu/14analysis/1Kints.txt
         *         https://algs4.cs.princeton.edu/14analysis/2Kints.txt
         *         https://algs4.cs.princeton.edu/14analysis/4Kints.txt
         *         https://algs4.cs.princeton.edu/14analysis/8Kints.txt
         *         https://algs4.cs.princeton.edu/14analysis/16Kints.txt
         *         https://algs4.cs.princeton.edu/14analysis/32Kints.txt
         *         https://algs4.cs.princeton.edu/14analysis/1Mints.txt
         */
        In in = null;
        try {
            in = new In("resources/ints/1Mints.txt");
            int[] a = in.readAllInts();
            Arrays.sort(a);

            System.out.println("Start TwoSumFast::count");
            int fastCount = execFunc(TwoSumFast::count, a);
            System.out.println("Start TwoSumFaster::count");
            int fasterCount = execFunc(TwoSumFaster::count, a);

            if (fastCount != fasterCount)
                throw new RuntimeException(fastCount + " != " + fasterCount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (in != null)
                in.close();
        }
    }

    private static int execFunc(Function<int[], Integer> function, int[] a) {
        StopwatchCPU stopwatchCPU = new StopwatchCPU();
        int count = function.apply(a);
        System.out.println("Time: " + stopwatchCPU.elapsedTime());
        return count;
    }
}
