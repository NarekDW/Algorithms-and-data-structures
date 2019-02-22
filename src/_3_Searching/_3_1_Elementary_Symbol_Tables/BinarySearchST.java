package _3_Searching._3_1_Elementary_Symbol_Tables;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;

@SuppressWarnings("Duplicates")
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int n;
    private Value lastValue;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public int size(Key k1, Key k2) {
        int r1 = rank(k1);
        int r2 = Math.min(rank(k2), n - 1);
        if (r2 < r1) return 0;
        return r2 - r1 + 1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0)
            return values[i];
        else
            return null;
    }

    public void put(Key key, Value value) {
        /*****************************************************************************************************
         * 3.1.28 Ordered insertions. Modify BinarySearchST so that inserting a key that is larg-
         * er than all keys in the table takes constant time (so that building a table by calling put()
         * for keys that are in order takes linear time).
         ****************************************************************************************************/
        if (!isEmpty() && key.compareTo(max()) > 0) {
            keys[n] = key;
            values[n] = value;
            n++;
            return;
        }

        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            values[i] = value;
            return;
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        n++;
    }

    public int rank(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    // Recursive solution
    public int rank(Key key, int lo, int hi) {
        if (lo > hi) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp > 0)
            return rank(key, mid + 1, hi);
        else if (cmp < 0)
            return rank(key, lo, mid - 1);
        else
            return mid;
    }

    /*****************************************************************************************************
     * 3.1.16 Implement the delete() method for BinarySearchST .
     ****************************************************************************************************/
    public void delete(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            keys[n - 1] = null;
            values[n - 1] = null;
            n--;
        }
    }


    /*****************************************************************************************************
     * 3.1.17 Implement the floor() method for BinarySearchST .
     ****************************************************************************************************/
    public Key floor(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return keys[i];
        if (i == 0) return null;
        return keys[i - 1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == n - 1) return null;
        return keys[i];
    }

    public boolean contains(Key key) {
        Value value = get(key);
        if (value != null)
            lastValue = value;
        return value != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(keys[i]);
        return queue;
    }

    public Iterable<Key> keys(Key k1, Key k2) {
        Queue<Key> queue = new Queue<>();
        int r1 = rank(k1);
        int r2 = rank(k2);
        for (int i = r1; i <= Math.min(r2, n - 1); i++)
            queue.enqueue(keys[i]);
        return queue;
    }

    public Key max() {
        return keys[n - 1];
    }

    public Key min() {
        return keys[0];
    }

    public Key select(int i) {
        return keys[i];
    }

    public Value getLastValue() {
        return lastValue;
    }

    public void deleteMin() {
        delete(min());
    }

    private void show() {
        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + " " + values[i]);
        }
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, String> bs = new BinarySearchST<>(3);
        bs.put(1, "a");
        bs.put(2, "b");
        bs.put(3, "c");

        bs.delete(2);
        bs.show();
    }
}
