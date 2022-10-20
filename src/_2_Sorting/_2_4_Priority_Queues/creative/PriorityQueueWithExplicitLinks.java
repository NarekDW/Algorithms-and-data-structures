package _2_Sorting._2_4_Priority_Queues.creative;

import common.StdRandom;

import static common.SortUtils.isSorted;
import static java.lang.Math.log;
import static java.lang.Math.pow;

/*****************************************************************************************************
 *
 * 2.4.24 Priority queue with explicit links. Implement a priority queue using a heap-
 * ordered binary tree, but use a triply linked structure instead of an array. You will need
 * three links per node: two to traverse down the tree and one to traverse up the tree. Your
 * implementation should guarantee logarithmic running time per operation, even if no
 * maximum priority-queue size is known ahead of time.
 *
 ****************************************************************************************************/
public class PriorityQueueWithExplicitLinks<Key extends Comparable<Key>> {

    private Node pq;
    private int n;

    // ~ log(n)
    public void insert(Key v) {
        Node node = new Node(v);
        if (pq != null) {
            Node last = getLastRoot(pq);
            if (last.left == null)
                last.left = node;
            else
                last.right = node;
            node.parent = last;
        } else
            pq = node;
        n++;
        swim(node);
    }

    // ~ log(n)
    public Key delMax() {
        Key key = pq.key;
        Node lastRoot = getLastNode(pq);
        Node last;
        if (lastRoot.left == null && lastRoot.right == null)
            last = lastRoot;
        else
            last = lastRoot.right == null ? lastRoot.left : lastRoot.right;
        exch(last, pq);
        removeNode(last);
        sink(pq);
        n--;
        return key;
    }

    private void swim(Node k) {
        while (k.parent != null && less(k.parent.key, k.key)) {
            exch(k.parent, k);
            k = k.parent;
        }
    }

    private void sink(Node k) {
        while (k.left != null) {
            Node node;
            if (k.right != null && less(k.left.key, k.right.key))
                node = k.right;
            else
                node = k.left;

            if (less(node.key, k.key)) break;
            exch(k, node);
            k = node;
        }
    }

    private void removeNode(Node last) {
        Node parent = last.parent;
        if (parent != null)
            if (parent.right != null)
                parent.right = null;
            else
                parent.left = null;
        last.parent = null;
    }

    // Find the last Node for inserting a new one at the end
    private Node getLastRoot(Node head) {
        int elems = n + 1;                              // Amount of current elements
        int height = (int) (log(elems) / log(2) + 1);   // Height of the tree
        int maxElems = (int) pow(2, height);            // Maximum capacity of elements for current height
        int half = (int) pow(2, height - 1) / 2;        // Half at the lowest level
        int free = maxElems - elems;                    // Amount of free elems
        head = findNode(head, half, free, free <= half);
        return head;
    }

    // Find the last Node for removing
    private Node getLastNode(Node head) {
        int elems = n + 1;                              // Amount of current elements
        int height = (int) (log(elems) / log(2) + 1);   // Height of the tree
        int maxElems = (int) pow(2, height);            // Maximum capacity of elements for current height
        int half = (int) pow(2, height - 1) / 2;        // Half at the lowest level
        int free = maxElems - elems;                    // Amount of free elems
        if (free == half * 2)
            free = 0;
        head = findNode(head, half, free, free < half);
        return head;
    }

    private Node findNode(Node head, int half, int i, boolean less) {
        while (half > 1) {
            // If free less then half -> move to the right,
            //                   else -> move to the left
            if (less)
                head = head.right;
            else
                head = head.left;
            i = i - half;
            half = half / 2;
        }
        return head;
    }

    private void exch(Node n, Node k) {
        Key tmp = n.key;
        n.key = k.key;
        k.key = tmp;
    }

    public static boolean less(Comparable v, Comparable w) {
        if (v == null)
            return true;
        else if (w == null)
            return false;
        else
            return v.compareTo(w) < 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private class Node {
        Node parent;
        Key key;
        Node left;
        Node right;

        Node(Key key) {
            this.key = key;
        }
    }


    // Test
    public static void main(String[] args) {
        PriorityQueueWithExplicitLinks<Integer> pq = new PriorityQueueWithExplicitLinks<>();


        int size = 10;
        Integer[] res = new Integer[size];
        for (int i = 0; i < size; i++)
            pq.insert(StdRandom.uniform(20));

        int i = size;
        while (!pq.isEmpty()) {
            Integer max = pq.delMax();
            res[--i] = max;
            System.out.println(max);
        }

        System.out.println(isSorted(res));
    }
}
