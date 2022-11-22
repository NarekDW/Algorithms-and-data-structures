package _2_Sorting._2_4_Priority_Queues.creative;

import common.SortUtils;

import static common.SortUtils.show;

/*****************************************************************************************************
 * <p>
 * 2.4.26 Heap without exchanges. Because the exch() primitive is used in the sink() and swim()
 * operations, the items are loaded and stored twice as often as necessary. Give more efficient
 * implementations that avoid this inefficiency, a la insertion sort (see Exercise 2.1.25).
 *
 ****************************************************************************************************/

public class HeapWithoutExchanges {

    public static <T extends Comparable<T>> void sort(T[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--)
            sink(pq, k, n);
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] pq, int k, int n) {
        T item = pq[k - 1];
        int index = k;
        while (2 * index <= n) {
            int j = 2 * index;
            if (j < n && less(pq, j - 1, j)) j++;
            if (!less(item, pq[j - 1])) break;
            pq[index - 1] = pq[j - 1];
            index = j;
        }

        pq[index - 1] = item;
    }

    private static <T extends Comparable<T>> boolean less(T[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static <T extends Comparable<T>> boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }


    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split("\\s");
        HeapWithoutExchanges.sort(a);
        show(a);

        test();
    }

    private static void test() {
        SortUtils.testRandomArray(HeapWithoutExchanges::sort, 10, 1_000_000);
    }
}
