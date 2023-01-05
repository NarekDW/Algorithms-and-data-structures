package _3_Searching._3_2_Binary_Search_Trees.experiments;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;

/*****************************************************************************************************
 * <p>
 * 3.2.41 Array representation. Develop a BST implementation that represents the BST
 * with three arrays (preallocated to the maximum size given in the constructor): one with
 * the keys, one with array indices corresponding to left links, and one with array indices
 * corresponding to right links. Compare the performance of your program with that of
 * the standard implementation.
 *
 ****************************************************************************************************/
public class ArrayRepresentationBST<Key extends Comparable<Key>, Value> {
    private final Key[] keys;
    private final Value[] values;
    private final int[] left;
    private final int[] right;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayRepresentationBST(int size) {
        keys = (Key[]) new Comparable[size + 1];
        values = (Value[]) new Object[size + 1];
        left = new int[size + 1];
        right = new int[size + 1];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (n == 0) return null;

        int i = 1;
        while (i <= n) {
            if (i == 0) return null;
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) i = left[i];
            else if (cmp > 0) i = right[i];
            else return values[i];
        }

        return null;
    }

    public void put(Key key, Value value) {
        int i = 1;

        if (n == 0) {
            n++;
            keys[n] = key;
            values[n] = value;
            return;
        }

        while (i <= n) {
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) {
                int tmp = i;
                i = left[i];

                if (i == 0) {
                    n++;
                    keys[n] = key;
                    values[n] = value;
                    left[tmp] = n;
                    return;
                }
            } else if (cmp > 0) {
                int tmp = i;
                i = right[i];

                if (i == 0) {
                    n++;
                    keys[n] = key;
                    values[n] = value;
                    right[tmp] = n;
                    return;
                }
            } else {
                values[i] = value;
                return;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        int i = 1;
        while (i < n && left[i] != 0)
            i = left[i];
        return keys[i];
    }

    public Key max() {
        int i = 1;
        while (i < n && right[i] != 0)
            i = right[i];
        return keys[i];
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(1, queue, lo, hi);
        return queue;
    }

    private void keys(int x, Queue<Key> queue, Key lo, Key hi) {
        if (x == 0) return;
        int cmplo = lo.compareTo(keys[x]);
        int cmphi = hi.compareTo(keys[x]);
        if (cmplo < 0) keys(left[x], queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(keys[x]);
        if (cmphi > 0) keys(right[x], queue, lo, hi);
    }


    // Test
    public static void main(String[] args) {
        ArrayRepresentationBST<Integer, String> ar = new ArrayRepresentationBST<>(10);
        ar.put(2, "B");
        ar.put(1, "A");
        ar.put(3, "C");

        System.out.println(ar.get(3));
        System.out.println(ar.get(1));
        System.out.println(ar.get(2));
        System.out.println(ar.max());
        System.out.println(ar.min());

        for (Integer i : ar.keys())
            System.out.println("keys : " + i);
    }
}
