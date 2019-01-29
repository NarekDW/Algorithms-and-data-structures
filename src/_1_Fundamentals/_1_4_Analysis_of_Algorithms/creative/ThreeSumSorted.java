package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _1_Fundamentals._1_4_Analysis_of_Algorithms.ThreeSumFast;
import common.StdRandom;

import java.util.stream.IntStream;

/*****************************************************************************************************
 *
 * 1.4.15 Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that
 * uses a linear algorithm to count the pairs that sum to zero after the array is sorted (in-
 * stead of the binary-search-based linearithmic algorithm). Then apply a similar idea to
 * develop a quadratic algorithm for the 3-sum problem.
 *
 ****************************************************************************************************/
public class ThreeSumSorted {

    public static int count(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            int k = a.length - 1;
            while (j < k) {
                int x = a[i] + a[j];
                if (x >= 0) break;
                if (Math.abs(x) < Math.abs(a[k])) k--;
                else if (Math.abs(x) > Math.abs(a[k])) j++;
                else if (x + a[k] == 0) {
                    cnt++;
                    j++;
                    k--;
                } else {
                    j++;
                    k--;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] x = IntStream.generate(() -> StdRandom.uniform(-1_000_000, 1_000_000))
                .limit(100_000)
                .distinct()
                .toArray();

        System.out.println("x.length = " + x.length);

        Stopwatch stopwatch = new Stopwatch();
        // ~ N^2 log(N) (346 sec)
        int count = ThreeSumFast.count(x);
        double v = stopwatch.elapsedTime();
        System.out.println("count = " + count + " : time = " + v);

        // Faster
        Stopwatch stopwatch2 = new Stopwatch();
        // ~ N^2 (20 sec)
        int cntSorted = count(x);
        double v2 = stopwatch2.elapsedTime();
        System.out.println("cntSorted = " + cntSorted + " : time = " + v2);


    }

}
