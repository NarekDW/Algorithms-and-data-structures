package _3_Searching._3_4_Hash_Tables.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _2_Sorting._2_3_Quicksort.Quick;

/*****************************************************************************************************
 *
 * 3.4.26 Lazy delete for linear probing. Add to LinearProbingHashST a delete()
 * method that deletes a key-value pair by setting the value to null (but not removing
 * the key) and later removing the pair from the table in resize() . Your primary chal-
 * lenge is to decide when to call resize() . Note : You should overwrite the null value if
 * a subsequent put() operation associates a new value with the key. Make sure that your
 * program takes into account the number of such tombstone items, as well as the number
 * of empty positions, in making the decision whether to expand or contract the table.
 *
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
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int s) {
        LinearProbingHashST<Key, Value> tmp = new LinearProbingHashST<>(s);
        for (int i = 0; i < m; i++)
            if (keys[i] != null && values[i] != null)
                tmp.put(keys[i], values[i]);
        keys = tmp.keys;
        values = tmp.values;
        m = tmp.m;
    }

    public void put(Key key, Value value) {
        if (n > m / 2) resize(2 * m);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (key.equals(keys[i]) || values[i] == null) {
                keys[i] = key;
                values[i] = value;
                return;
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
        values[hash(key)] = null;
        n--;
        if (n < m / 8) resize(m / 2);
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

}
