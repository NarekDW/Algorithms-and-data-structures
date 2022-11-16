package _2_Sorting._2_3_Quicksort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;
import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.StdRandom;

import static common.SortUtils.*;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 * <p>
 * 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based
 * on a main loop where a subarray is popped from a stack to be partitioned, and the resulting
 * subarrays are pushed onto the stack. Note : Push the larger of the subarrays onto
 * the stack first, which guarantees that the stack will have at most lg N entries.
 *
 ****************************************************************************************************/
public class NonrecursiveQuicksort {

    private static final int M = 10;

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a.length < 2)
            return;

        shuffle(a);
        Stack<Integer> bounds = new Stack<>();
        bounds.push(0);
        bounds.push(a.length - 1);

        while (!bounds.isEmpty()) {
            Integer hi = bounds.pop();
            Integer lo = bounds.pop();
            if (lo >= hi) continue;

            if (hi <= lo + M) {
                Insertion.sort(a, lo, hi);
                continue;
            }

            int j = partition(a, lo, hi);

            // put [lo, j - 1] to the stack
            bounds.push(lo);
            bounds.push(j - 1);

            // put [j + 1, hi] to the stack
            bounds.push(j + 1);
            bounds.push(hi);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        T v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            int s = 1_000;
            Integer[] x = new Integer[s];
            for (int j = 0; j < s; j++)
                x[j] = StdRandom.uniform(s);

            sort(x);

            if (!isSorted(x)) {
                show(x);
                throw new RuntimeException();
            }
        }
    }
}
