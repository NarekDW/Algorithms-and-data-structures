package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises.DoubleLinked;
import common.SortUtils;
import common.StdRandom;

import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.2.17 Linked-list sort. Implement a natural mergesort for linked lists. (This is the
 * method of choice for sorting linked lists because it uses no extra space and is guaranteed
 * to be linearithmic.)
 *
 ****************************************************************************************************/
public class LinkedListSort {

    public static <T extends Comparable<T>> void sort(DoubleLinked<T> linked) {
        int n = linked.size();
        for (int sz = 1; sz < n; sz = sz + sz) // sz: subarray size
            for (int lo = 0; lo < n - sz; lo += sz + sz) // lo: subarray index
                merge(linked, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
    }

    private static <T extends Comparable<T>> void merge(DoubleLinked<T> linked, int lo, int mid, int hi) {
        DoubleLinked<T>.DoubleNode left = null;
        DoubleLinked<T>.DoubleNode right = null;
        DoubleLinked<T>.DoubleNode current = linked.first;

        for (int i = 0; i <= mid + 1; i++) {
            if (i == lo)
                left = current;
            if (i == mid + 1)
                right = current;
            current = current.next;
        }

        int leftIndex = lo, rightIndex = hi;
        for (int i = lo; i <= hi; i++)
            if (leftIndex > mid || rightIndex < mid + 1)
                break;
            else if (less(right.item, left.item)) {
                DoubleLinked<T>.DoubleNode tmp = right.next;

                // extract item from right half
                right.prev.next = right.next;
                if (right.next != null)
                    right.next.prev = right.prev;

                // insert item to the left half
                right.next = left;
                if (left.prev != null)
                    left.prev.next = right;
                right.prev = left.prev;
                left.prev = right;

                // move the initial reference to the root of the linked list
                if (right.prev == null)
                    linked.first = right;

                // move right reference to the next item
                right = tmp;
                rightIndex--;
            } else {
                left = left.next;
                leftIndex++;
            }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            int size = StdRandom.uniform(0, 1_000);
            DoubleLinked<Integer> linked = new DoubleLinked<>();

            for (int j = 0; j < size; j++)
                linked.addFirst(StdRandom.uniform(1000));

            sort(linked);

            if (!SortUtils.isSorted(linked))
                throw new RuntimeException("Not Sorted!");
        }
    }
}
