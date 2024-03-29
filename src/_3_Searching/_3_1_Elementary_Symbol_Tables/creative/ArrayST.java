package _3_Searching._3_1_Elementary_Symbol_Tables.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import common.StdIn;
import common.StdOut;

/******************************************************************************
 *  Compilation:  javac ArrayST.java
 *  Execution:    java ArrayST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 *
 *  Symbol table implementation with unordered array. Uses repeated
 *  doubling to resize the array.
 *
 *  % java ArrayST < tiny.txt
 *  S 0
 *  H 5
 *  X 7
 *  R 3
 *  C 4
 *  L 11
 *  A 8
 *  M 9
 *  P 10
 *  E 12
 *
 ******************************************************************************/

public class ArrayST<Key, Value> {
    private static final int INIT_SIZE = 8;

    // Unordered
    private Value[] vals;   // symbol table values
    private Key[] keys;     // symbol table keys
    private int n = 0;      // number of elements in symbol table

    public ArrayST() {
        keys = (Key[])   new Object[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
    }

    // return the number of key-value pairs in the symbol table
    public int size() {
        return n;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // resize the parallel arrays to the given capacity
    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            tempk[i] = keys[i];
        for (int i = 0; i < n; i++)
            tempv[i] = vals[i];
        keys = tempk;
        vals = tempv;
    }

    // insert the key-value pair into the symbol table
    public void put(Key key, Value val) {

        // to deal with duplicates
        delete(key);

        // double size of arrays if necessary
        if (n >= vals.length) resize(2*n);

        // add new key and value at the end of array
        vals[n] = val;
        keys[n] = key;
        n++;
    }

    /*****************************************************************************************************
     * <p>
     * 3.1.22 Self-organizing search. A self-organizing search algorithm is one that rearranges
     * items to make those that are accessed frequently likely to be found early in the search.
     * Modify your search implementation for Exercise 3.1.2 to perform the following action
     * on every search hit: move the key-value pair found to the beginning of the list, moving
     * all pairs between the beginning of the list and the vacated position to the right one
     * position. This procedure is called the move-to-front heuristic.
     *
     ****************************************************************************************************/
    public Value get(Key key) {
        for (int i = 0; i < n; i++)
            if (keys[i].equals(key)) {
                Value val = vals[i];
                move(i);
                return val;
            }
        return null;
    }

    private void move(int i) {
        Key key = keys[i];
        Value value = vals[i];
        for (int j = i; j > 0; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[0] = key;
        vals[0] = value;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(keys[i]);
        return queue;
    }

    // remove given key (and associated value)
    public void delete(Key key) {
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[n-1];
                vals[i] = vals[n-1];
                keys[n-1] = null;
                vals[n-1] = null;
                n--;
                if (n > 0 && n == keys.length/4) resize(keys.length/2);
                return;
            }
        }
    }




    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
