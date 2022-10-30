package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import common.StdRandom;

import java.util.function.BiFunction;

import static common.SortUtils.*;

/*****************************************************************************************************
 * <p>
 * 2.2.14 Merging sorted queues. Develop a static method that takes two queues of sorted
 * items as arguments and returns a queue that results from merging the queues into
 * sorted order.
 *
 ****************************************************************************************************/
public class MergingSortedQueues {

    public static <T extends Comparable<T>> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        Queue<T> result = new Queue<>();
        if (q1.isEmpty() && q2.isEmpty())
            return result;

        T item1 = null;
        T item2 = null;
        while (!q1.isEmpty() || !q2.isEmpty()) {
            item1 = item1 == null ? q1.dequeue() : item1;
            item2 = item2 == null ? q2.dequeue() : item2;

            if (item1 == null) {
                result.enqueue(item2);
                item2 = null;
            } else if (item2 == null) {
                result.enqueue(item1);
                item1 = null;
            } else {
                if (less(item1, item2)) {
                    result.enqueue(item1);
                    item1 = null;
                } else {
                    result.enqueue(item2);
                    item2 = null;
                }
            }
        }

        if (item1 != null)
            result.enqueue(item1);
        if (item2 != null)
            result.enqueue(item2);

        return result;
    }

    public static <T extends Comparable<T>> Queue<T> mergeRecursive(Queue<T> q1, Queue<T> q2) {
        T firstItem = q2.isEmpty() ? q1.dequeue() : q2.dequeue();
        return mergeRecursive(q1, q2, firstItem, new Queue<>());
    }

    private static <T extends Comparable<T>> Queue<T> mergeRecursive(Queue<T> q1, Queue<T> q2, T currentItem, Queue<T> res) {
        if (q1.isEmpty() && q2.isEmpty()) {
            if (currentItem != null)
                res.enqueue(currentItem);
            return res;
        }

        T t;
        if (q1.isEmpty() || q2.isEmpty()) {
            T nextItem = q1.isEmpty() ? q2.dequeue() : q1.dequeue();
            T itemToPut = less(currentItem, nextItem) ? currentItem : nextItem;
            T itemToPass = less(currentItem, nextItem) ? nextItem : currentItem;
            res.enqueue(itemToPut);
            return mergeRecursive(q1, q2, itemToPass, res);
        } else {
            t = q1.dequeue();
            if (less(t, currentItem)) {
                res.enqueue(t);
                return mergeRecursive(q1, q2, currentItem, res);
            } else {
                res.enqueue(currentItem);
                return mergeRecursive(q2, q1, t, res);
            }
        }
    }


    public static void main(String[] args) {
        testMerge(MergingSortedQueues::merge);
        testMerge(MergingSortedQueues::mergeRecursive);
    }

    private static void testMerge(BiFunction<Queue<Double>, Queue<Double>, Queue<Double>> mergeFunction) {
        for (int i = 0; i < 10_000; i++) {
            Queue<Double> q1 = generateQueue();
            Queue<Double> q2 = generateQueue();
            int q1Size = q1.size();
            int q2Size = q2.size();
            Queue<Double> resultQueue = mergeFunction.apply(q1, q2);
            checkSize(resultQueue, q1Size + q2Size);
            checkOrdering(resultQueue);
        }
    }

    private static Queue<Double> generateQueue() {
        int queueSize = StdRandom.uniform(0, 10);
        Double[] array = generateArrayDouble(queueSize, true);
        Queue<Double> queue = new Queue<>();
        for (Double item : array)
            queue.enqueue(item);
        return queue;
    }

    private static void checkOrdering(Queue<Double> queue) {
        if (!isSorted(queue))
            throw new RuntimeException();
    }

    private static void checkSize(Queue<Double> result, int expected) {
        if (result.size() != expected)
            throw new RuntimeException();
    }

}
