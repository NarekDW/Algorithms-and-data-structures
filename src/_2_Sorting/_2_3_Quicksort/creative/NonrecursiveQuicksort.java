package _2_Sorting._2_3_Quicksort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;
import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.StdRandom;

import static common.SortUtils.*;
import static common.StdRandom.shuffle;

/*****************************************************************************************************
 *
 * 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based
 * on a main loop where a subarray is popped from a stack to be partitioned, and the re-
 * sulting subarrays are pushed onto the stack. Note : Push the larger of the subarrays onto
 * the stack first, which guarantees that the stack will have at most lg N entries.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class NonrecursiveQuicksort {

    private static final int M = 10;

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        Stack<Integer> bounds = new Stack<>();
        bounds.push(lo);
        bounds.push(hi);

        while (!bounds.isEmpty()) {
            Integer l = bounds.pop();
            Integer h = bounds.pop();

            if (h <= l + M) {
                Insertion.sort(a, lo, hi);
                break;
            }

            int j = partition(a, l, h);

            if (h - j > j - l) {
                if (j + 1 < h) {
                    bounds.push(j + 1);
                    bounds.push(h);
                }

                if (j - 1 > l) {
                    bounds.push(l);
                    bounds.push(j - 1);
                }

            } else {
                if (j - 1 > l) {
                    bounds.push(l);
                    bounds.push(j - 1);
                }

                if (j + 1 < h) {
                    bounds.push(j + 1);
                    bounds.push(h);
                }
            }

        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
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
