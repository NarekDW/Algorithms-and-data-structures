package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;

import static _2_Sorting._2_1_Elementary_Sorts.SortUtils.less;

/*****************************************************************************************************
 *
 * 2.2.14 Merging sorted queues. Develop a static method that takes two queues of sorted
 * items as arguments and returns a queue that results from merging the queues into
 * sorted order.
 *
 ****************************************************************************************************/
public class MergingSortedQueues {

    public static <T extends Comparable> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        return merge(q1, q2, q2.dequeue(), new Queue<>());
    }

    private static <T extends Comparable> Queue<T> merge(Queue<T> q1, Queue<T> q2, T item, Queue<T> res) {
        if (q1.isEmpty() && q2.isEmpty()) {
            res.enqueue(item);
            return res;
        }

        T t;
        if (q1.isEmpty())
            t = q2.dequeue();
        else if (q2.isEmpty())
            t = q1.dequeue();
        else {
            t = q1.dequeue();
            if (less(t, item)) {
                res.enqueue(t);
                return merge(q1, q2, item, res);
            } else {
                res.enqueue(item);
                return merge(q2, q1, t, res);
            }
        }

        if (less(item, t))
            res.enqueue(item);
        else {
            res.enqueue(t);
            t = item;
        }
        return merge(q1, q2, t, res);
    }


    public static void main(String[] args) {
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();

        q1.enqueue(1);
        q1.enqueue(3);
        q1.enqueue(5);

        q2.enqueue(2);
        q2.enqueue(4);
        q2.enqueue(6);
        q2.enqueue(6);
        q2.enqueue(8);

        Queue<Integer> merge = merge(q1, q2);
        for (Integer i : merge)
            System.out.print(i + " ");
    }
}
