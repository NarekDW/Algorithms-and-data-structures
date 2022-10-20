package _2_Sorting._2_1_Elementary_Sorts.exercises;

import _2_Sorting._2_1_Elementary_Sorts.SortUtils;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.exch;
import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.1.11 Implement a version of shellsort that keeps the increment sequence in an array,
 * rather than computing it.
 * <p>
 * 2.1.3 Give an example of an array of N items that maximizes the number of times the test
 * a[j] < a[min] fails (and, therefore, min gets updated) during the operation of selection
 * sort (Algorithm 2.1).
 * [2, 3, 4, 5, 6, 7, 8, 9, 10, 1]
 *
 ****************************************************************************************************/
@SuppressWarnings("rawtypes")
public class ShellSortWithArray {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int size = calculateHArraySize(n);
        int[] hs = new int[size];
        for (int i = 0, h = 1; h < n; i++, h = 3 * h + 1)
            hs[i] = h;

        for (int h : hs)
            for (int j = h; j < n; j++)
                for (int k = j; k >= h && less(a[k], a[k - h]); k -= h)
                    exch(a, k, k - h);
    }

    /**
     * It's Linear recurrent equations of the first order.
     * x[n] = a*x[n-1] + b
     * lets say x[n] = y[n] + s
     * where y[n] - new variable
     * s    - constant
     * then we have: y[n] + s = a*y[n-1] + a*s + b
     * y[n] = a*y[n-1] + s*(a - 1) + b
     * Lets put s*(a - 1) + b = 0
     * then s = b / (1 - a)
     * So:
     * y[n] = a*y[n-1] (Geometric progression)
     * y[n] = a^n *y[0]
     * y[n] = x[n] - s => x[n] - s = a^n * (x[0] - s)
     * x[n] = a^n * (x[0] - s) + s
     * then we can substitute s = b / (1 - a)
     * And we get equation like:
     * x[n] = a^n * (x[0] - b / (1 - a)) + b / (1 - a)
     * <p>
     * In our case for calculation h, we have initial values as:
     * h[0] = 0;
     * a  = 3;
     * b  = 1;
     * Then:
     * h[n] = 3^n * (0 - 1/(-2)) + 1/(-2)
     * h[n] = 3^n * 1/2 - 1/2
     * h[n] = (3^n - 1) / 2
     * <p>
     * For calculation n (number of h's required for Shell sort/ array size to store h's), we need to solve
     * mathematical inequality:
     * (3^n - 1) / 2 <= initialArraySize
     * n = log3(2*initialArraySize + 1)
     */
    private static int calculateHArraySize(int initialArraySize) {
        return (int) (Math.log(2 * initialArraySize + 1) / Math.log(3));
    }

    public static void main(String[] args) {
        testCalculateHArraySize();
        int size = 100_000;
        Double[] doubles = SortUtils.generateArray(size);
        sort(doubles);
        if (!SortUtils.isSorted(doubles))
            throw new RuntimeException();
    }

    private static void testCalculateHArraySize() {
        if (calculateHArraySize(10) != 2)
            throw new RuntimeException();
        if (calculateHArraySize(100) != 4)
            throw new RuntimeException();
        if (calculateHArraySize(300) != 5)
            throw new RuntimeException();
        if (calculateHArraySize(1111) != 7)
            throw new RuntimeException();
        if (calculateHArraySize(21111) != 9)
            throw new RuntimeException();
    }

}