package _2_Sorting._2_1_Elementary_Sorts.exercises;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static common.SortUtils.show;

/*****************************************************************************************************
 *
 * 7. Stoogesort.
 * Analyze the running time and correctness of the following recursive sorting algorithm:
 * if the leftmost item is larger than the rightmost item, swap them.
 * If there are 2 or more items in the current subarray,
 * (i) sort the initial two-thirds of the array recursively,
 * (ii) sort the final two-thirds of the array,
 * (iii) sort the initial two-thirds of the array again.
 *
 ****************************************************************************************************/
public class StoogeSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /*****************************************************************************************************
     * Complexity:
     * This algorithm makes exactly one comparison on each step.
     * On each step - algorithm divide initial array of size n - on 3 arrays with (2*n / 3) sizes:
     *                                           N
     *                                       /   |   \
     *                                   2N/3  2N/3  2N/3
     *                                   /
     *                              (2N/3)2/3
     *                                 /
     *                               ...
     *                               /
     *                           N * (2/3)^k = 1
     * By considering this tree we can find the depth - k:
     * N * (2/3)^k = 1                  =>>
     * N = 1 / (2/3)^k                  =>>
     * N = (3/2)^k                      =>>
     * log(3/2, N) = log(3/2, (3/2)^k)  =>>
     * k = log(3/2, N) (!!!)
     *
     * On each step algorithm makes 3^k comparisons =>> on the last step we will get:
     *                                     3^(log(3/2, N)) =>> N^(log(3/2, 3))
     * comparisons.
     *
     * We can compute the full work:
     * 1 + 3 + 9 + ... + 3^(log(3/2, N))
     * by using geometric progression formulas.
     *
     ****************************************************************************************************/
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        if (less(a[hi], a[lo])) exch(a, hi, lo);
        if (hi - lo + 1 > 2) {
            int t = (hi - lo + 1) / 3;
            sort(a, lo, hi - t);
            sort(a, lo + t, hi);
            sort(a, lo, hi - t);
        }
    }

    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 5, 6, 4};
        sort(x);
        show(x);
    }
}
