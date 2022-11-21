package _2_Sorting._2_4_Priority_Queues;

import common.SortUtils;

import static common.SortUtils.show;

/*****************************************************************************************************
 * <p>
 * 2.4.13 Describe a way to avoid the j < N test in sink() .
 * <p>
 * Answer: change 2*k <= n to 2*k < n
 ****************************************************************************************************/
public class Heap {

    // This class should not be instantiated.
    private Heap() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param pq the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] pq) {
        int n = pq.length;
        // The for loop constructs the heap
        for (int k = n / 2; k >= 1; k--)
            sink(pq, k, n);
        /*
        Reheapify:
            A binary tree is heap-ordered if the key in each node is larger than or
            equal to the keys in that node’s two children (if any).
        On each step we select the greatest element which is on position 1
        and move it to the end.
        Afterwards we call sink to 'reheapify' the heap.
         */
        while (n > 1) {
            exch(pq, 1, n--);
            // we can avoid that sink, just select the next max child
            sink(pq, 1, n);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static <T extends Comparable<T>> void sink(T[] pq, int k, int n) {
        while (2 * k < n) {
            int j = 2 * k;
            if (less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }

        if (2 * k == n)
            if (less(pq, k, 2 * k))
                exch(pq, k, 2 * k);
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static <T extends Comparable<T>> boolean less(T[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }


    /**
     * Reads in a sequence of strings from standard input; heapsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split("\\s");
        Heap.sort(a);
        show(a);

        test();
    }

    private static void test() {
        SortUtils.testRandomArray(Heap::sort, 10, 1_000_000);
    }

}
