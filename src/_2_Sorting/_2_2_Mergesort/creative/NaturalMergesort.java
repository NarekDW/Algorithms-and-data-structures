package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import common.SortUtils;
import common.StdRandom;

import java.util.Arrays;

import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.2.16 Natural mergesort. Write a version of bottom-up mergesort that takes advantage of order
 * in the array by proceeding as follows each time it needs to find two arrays to merge:
 * find a sorted subarray (by incrementing a pointer until finding an entry that is smaller than
 * its predecessor in the array), then find the next, then merge them.
 * Analyze the running time of this algorithm in terms of the array size and the number of
 * maximal increasing sequences in the array.
 *
 ****************************************************************************************************/
public class NaturalMergesort {
    @SuppressWarnings("rawtypes")
    private static Comparable[] aux;

    public static <T extends Comparable<T>> T[] sort(T[] a) {
        int n = a.length;
        aux = new Comparable[n];
        Queue<Integer> indexes = new Queue<>();
        int index = 0;
        while (index != a.length) {
            indexes.enqueue(index);
            index = findIndex(a, index);
        }
        indexes.enqueue(index);

        while (indexes.size() > 2) {
            Integer lo = indexes.dequeue();
            Integer mid = indexes.peek();
            if (lo > mid) {
                indexes.enqueue(lo);
                continue;
            } else {
                indexes.dequeue();
            }

            Integer hi = indexes.peek();
            if (mid > hi) {
                indexes.enqueue(lo);
                indexes.enqueue(mid);
                continue;
            }

            merge(a, lo, mid, hi);
            indexes.enqueue(lo);
        }

        return a;
    }

    private static <T extends Comparable<T>> int findIndex(T[] a, int from) {
        int index;
        for (index = from + 1; index < a.length; index++)
            if (less(a[index], a[index - 1]))
                return index;

        return a.length;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(T[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid;
        //noinspection ManualArrayCopy
        for (int k = lo; k < hi; k++)
            aux[k] = a[k];

        for (int k = lo; k < hi; k++)
            if (j > hi - 1)
                a[k] = (T) aux[i++];
            else if (i > mid - 1)
                a[k] = (T) aux[j++];
            else if (less(aux[j], aux[i]))
                a[k] = (T) aux[j++];
            else
                a[k] = (T) aux[i++];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            int size = StdRandom.uniform(0, 1_000);
            Integer[] integers = SortUtils.generateArrayInteger(size);
            Integer[] res = sort(integers.clone());
            if (!SortUtils.isSorted(res)) {
                System.out.println(Arrays.toString(integers));
                System.out.println(Arrays.toString(res));
                throw new RuntimeException();
            }
        }
    }

}
