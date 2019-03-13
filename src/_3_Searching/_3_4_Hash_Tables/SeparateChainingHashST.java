package _3_Searching._3_4_Hash_Tables;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _2_Sorting._2_3_Quicksort.Quick;
import _3_Searching._3_1_Elementary_Symbol_Tables.SequentialSearchST;
import _3_Searching._3_3_Balanced_Search_Trees.RedBlackBSTOrigin;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
//    private SequentialSearchST<Key, Value>[] st;
    private RedBlackBSTOrigin<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
//        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        st = (RedBlackBSTOrigin<Key, Value>[]) new RedBlackBSTOrigin[m];
        for (int i = 0; i < m; i++)
//            st[i] = new SequentialSearchST<>();
            st[i] = new RedBlackBSTOrigin<>();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (n >= 10*m) resize(2*m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, value);
    }

    /*****************************************************************************************************
     * 3.4.9 Implement an eager delete() method for SeparateChainingHashST.
     ****************************************************************************************************/
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
    }


    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return n;
    }

    // 3.4.19
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }


    public double pirson() {
        double t = (n * 1.0) / m;
        double sum = 0;
        for (RedBlackBSTOrigin<Key, Value> s : st) {
            int f = s.size();
            sum += Math.pow((f - t), 2);
        }
        System.out.println("M = " + m);
        return (m * 1.0 / n) * sum;
    }

}
