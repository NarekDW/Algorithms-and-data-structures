package _3_Searching;

import _3_Searching._3_1_Elementary_Symbol_Tables.exercises.OrderedSequentialSearchST;

public interface SymbolTable<Key extends Comparable<Key>, Value> {
    void put(Key key, Value val);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Iterable<Key> keys();
}
