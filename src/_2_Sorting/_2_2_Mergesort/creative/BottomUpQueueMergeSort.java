package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;

import static common.SortUtils.*;

import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 2.2.15 Bottom-up queue mergesort.
 * Develop a bottom-up mergesort implementation based on the following approach:
 * Given N items, create N queues, each containing one of the items. Create a queue of the N queues.
 * Then repeatedly apply the merging operation of Exercise 2.2.14 to the first two queues and
 * reinsert the merged queue at the end. Repeat until the queue of queues contains only one queue.
 *
 ****************************************************************************************************/

public class BottomUpQueueMergeSort {

    public static <T extends Comparable<T>> Queue<T> sort(T[] a) {
        Queue<Queue<T>> baseQueue = new Queue<>();
        for (T i : a) {
            Queue<T> localQueue = new Queue<>();
            localQueue.enqueue(i);
            baseQueue.enqueue(localQueue);
        }

        while (baseQueue.size() > 1) {
            Queue<T> merged = MergingSortedQueues.mergeRecursive(baseQueue.dequeue(), baseQueue.dequeue());
            baseQueue.enqueue(merged);
        }

        return baseQueue.dequeue();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            int size = StdRandom.uniform(100, 1_000);
            Integer[] integers = generateArrayInteger(size);

            Queue<Integer> result = sort(integers);
            if (!isSorted(result))
                throw new RuntimeException();
        }
    }
}
