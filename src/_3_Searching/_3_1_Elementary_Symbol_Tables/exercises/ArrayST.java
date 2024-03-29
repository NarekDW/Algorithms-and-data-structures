package _3_Searching._3_1_Elementary_Symbol_Tables.exercises;

import _3_Searching.SymbolTable;
import _3_Searching.SymbolTableTest;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 3.1.2 Develop a symbol-table implementation ArrayST that uses an (unordered) array as the
 * underlying data structure to implement our basic symbol-table API.
 *
 ****************************************************************************************************/
public class ArrayST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private final Key[] keys;
    private final Value[] values;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
        this.n = 0;
    }

    @Override
    public void put(Key key, Value val) {
        int rank = rank(key);
        if (rank == -1) {
            keys[n] = key;
            values[n] = val;
            n++;
        } else {
            values[rank] = val;
        }
    }

    @Override
    public Value get(Key key) {
        int rank = rank(key);
        if (rank == -1)
            return null;
        return values[rank];
    }

    @Override
    public void delete(Key key) {
        int rank = rank(key);
        if (rank == -1)
            return;

        for (int i = rank; i < n - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }

        keys[n - 1] = null;
        values[n - 1] = null;
        n--;
    }

    @Override
    public boolean contains(Key key) {
        return rank(key) != -1;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<Key> keys() {
        return () -> new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            public Key next() {
                return keys[i++];
            }
        };
    }

    private int rank(Key key) {
        for (int i = 0; i < n; i++)
            if (keys[i].compareTo(key) == 0)
                return i;
        return -1;
    }


    public static void main(String[] args) {
        SymbolTable<Integer, String> st = new ArrayST<>(10);
        SymbolTableTest.testST(st);
    }
}
