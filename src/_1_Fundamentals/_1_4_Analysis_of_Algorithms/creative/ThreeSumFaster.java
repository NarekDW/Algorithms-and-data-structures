package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.StopwatchCPU;
import _1_Fundamentals._1_4_Analysis_of_Algorithms.ThreeSumFast;
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
public class ThreeSumFaster {

    public static int count(int[] a) {
        int n = a.length;
        if (n > 0 && a[0] > 0)
            return 0;

        int count = 0;
        for (int k = 0; k < n; k++) {
            for (int i = k + 1, j = n - 1; i < j; ) {
                int left = a[k] + a[i];
                int right = a[j];
                if (left + right == 0) {
                    count++;
                    i++;
                    j--;
                } else if (Math.abs(right) > Math.abs(left))
                    j--;
                else
                    i++;
            }
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
            in = new In("resources/ints/32Kints.txt");
            int[] a = in.readAllInts();
            Arrays.sort(a);

            System.out.println("Start ThreeSumFast::count");
            int fastCount = execFunc(ThreeSumFast::count, a);
            System.out.println("Start ThreeSumFaster::count");
            int fasterCount = execFunc(ThreeSumFaster::count, a);

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
