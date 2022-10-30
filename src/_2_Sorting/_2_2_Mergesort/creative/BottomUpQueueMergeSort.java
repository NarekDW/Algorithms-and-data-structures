package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import common.StdRandom;

/*****************************************************************************************************
 *
 * 2.2.15 Bottom-up queue mergesort. Develop a bottom-up mergesort implementation
 * based on the following approach: Given N items, create N queues, each containing one
 * of the items. Create a queue of the N queues. Then repeatedly apply the merging opera-
 * tion of Exercise 2.2.14 to the first two queues and reinsert the merged queue at the end.
 * Repeat until the queue of queues contains only one queue.
 *
 ****************************************************************************************************/

public class BottomUpQueueMergeSort {

    public static Queue<Comparable> sort(Comparable[] a) {
        Queue<Queue<Comparable>> queue = new Queue<>();
        for (Comparable i : a) {
            Queue<Comparable> q = new Queue<>();
            q.enqueue(i);
            queue.enqueue(q);
        }

        while (queue.size() > 1) {
            Queue<Comparable> merged = MergingSortedQueues.mergeRecursive(queue.dequeue(), queue.dequeue());
            queue.enqueue(merged);
        }

        return queue.dequeue();
    }

    public static void main(String[] args) {
        int s = 100;
        Integer[] x = new Integer[s];
        for (int i = 0; i < s; i++) {
            x[i] = StdRandom.uniform(100);
        }

        Queue<Comparable> sort = sort(x);
        for (Comparable i : sort)
            System.out.print(i + " ");
    }

}
