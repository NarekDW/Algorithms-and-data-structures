package _3_Searching._3_5_Searching_Applications.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _2_Sorting._2_3_Quicksort.Quick;

/*****************************************************************************************************
 *
 * 3.5.8 Modify LinearProbingHashST to keep duplicate keys in the table. Return any
 * value associated with the given key for get() , and remove all items in the table that have
 * keys equal to the given key for delete() .
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class LinearProbingHashST<Key extends Comparable<Key>, Value> {

    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int m) {
        this.m = m;
        keys = (Key[]) new Comparable[m];
        values = (Value[]) new Comparable[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int s) {
        LinearProbingHashST<Key, Value> tmp = new LinearProbingHashST<>(s);
        for (int i = 0; i < m; i++)
            if (keys[i] != null)
                tmp.put(keys[i], values[i]);
        keys = tmp.keys;
        values = tmp.values;
        m = tmp.m;
    }

    public void put(Key key, Value value) {
        if (n > m / 2) resize(2 * m);
        int i = hash(key);
        while (keys[i] != null)
            i = (i + 1) % m;

        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (key.equals(keys[i]))
                return values[i];
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % m;


        // delete key and associated value
        keys[i] = null;
        values[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key keyToRehash = keys[i];
            Value valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;
        if (n > 0 && n <= m / 8) resize(m / 2);

        // Delete the rest of keys which equal to current key
        delete(key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        Key[] cmp = (Key[]) new Comparable[n];
        int j = 0;
        for (int i = 0; i < m; i++)
            if (keys[i] != null)
                cmp[j++] = keys[i];
        Quick.sort(cmp);
        Queue<Key> queue = new Queue<>();
        for (Key k : cmp)
            queue.enqueue(k);
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<Integer, String> st = new LinearProbingHashST<>();

        st.put(1, "a");
        st.put(2, "b");
        st.put(2, "c");
        st.put(2, "d");
        st.put(3, "e");
        st.put(3, "f");
        st.put(4, "g");

        st.keys().forEach(System.out::println);
        st.delete(2);
        System.out.println();
        st.keys().forEach(System.out::println);
        st.delete(3);
        System.out.println();
        st.keys().forEach(System.out::println);
    }
}
