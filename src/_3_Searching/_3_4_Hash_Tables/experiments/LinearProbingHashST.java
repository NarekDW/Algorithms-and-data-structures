package _3_Searching._3_4_Hash_Tables.experiments;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _2_Sorting._2_3_Quicksort.Quick;

@SuppressWarnings("Duplicates")
public class LinearProbingHashST<Key extends Comparable<Key>, Value> {

    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;
    int compares;

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


    public void put(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            compares++;
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }

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
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

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

    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    // 3.4.19
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
}
