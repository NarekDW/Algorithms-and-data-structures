package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import java.util.Arrays;

/*****************************************************************************************************
 *
 * 1.4.15 Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that
 * uses a linear algorithm to count the pairs that sum to zero after the array is sorted (in-
 * stead of the binary-search-based linearithmic algorithm). Then apply a similar idea to
 * develop a quadratic algorithm for the 3-sum problem.
 *
 ****************************************************************************************************/
public class TwoSumSorted {

    // linear
    public static int count(int[] a) {
        Arrays.sort(a);
        int cnt = 0;
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            if (a[i] >= 0) break;
            if (Math.abs(a[i]) < Math.abs(a[j])) j--;
            else if (Math.abs(a[i]) > Math.abs(a[j])) i++;
            else if (a[i] + a[j] == 0) {
                cnt++;
                i++;
                j--;
            } else {
                i++;
                j--;
            }
        }

        return cnt;
    }


}
