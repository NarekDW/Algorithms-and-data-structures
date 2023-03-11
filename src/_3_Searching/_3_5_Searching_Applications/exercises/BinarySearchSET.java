package _3_Searching._3_5_Searching_Applications.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;

/*****************************************************************************************************
 * <p>
 * 3.5.3 Develop a SET implementation BinarySearchSET by starting with the code for
 * BinarySearchST and eliminating all of the code involving values.
 *
 ****************************************************************************************************/
public class BinarySearchSET<Key extends Comparable<Key>> {

    private final Key[] keys;
    private int n;

    public BinarySearchSET(int capacity) {
        //noinspection unchecked
        keys = (Key[]) new Comparable[capacity];
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

    public void put(Key key) {
        if (!isEmpty() && key.compareTo(max()) > 0) {
            keys[n++] = key;
            return;
        }

        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) return;

        for (int j = n; j > i; j--)
            keys[j] = keys[j - 1];

        keys[i] = key;
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

    public void delete(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            for (int j = i; j < n - 1; j++)
                keys[j] = keys[j + 1];
            keys[n - 1] = null;
            n--;
        }
    }

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
        int i = rank(key);
        return keys[i].compareTo(key) == 0;
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

    public void deleteMin() {
        delete(min());
    }

    private void show() {
        for (int i = 0; i < keys.length; i++)
            System.out.println(keys[i]);
    }

    public static void main(String[] args) {
        BinarySearchSET<Integer> bs = new BinarySearchSET<>(3);
        bs.put(1);
        bs.put(2);
        bs.put(3);

        bs.delete(2);
//        bs.show();

        bs.keys().forEach(System.out::println);
    }

}
