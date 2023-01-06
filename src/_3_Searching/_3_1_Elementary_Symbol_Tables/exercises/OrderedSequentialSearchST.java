package _3_Searching._3_1_Elementary_Symbol_Tables.exercises;

import _3_Searching.SymbolTable;
import _3_Searching.SymbolTableTest;
import common.SimpleNode;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 3.1.3 Develop a symbol-table implementation OrderedSequentialSearchST that uses an ordered linked
 * list as the underlying data structure to implement our ordered symbol-table API.
 *
 ****************************************************************************************************/
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private SimpleNode<Key, Value> first;
    private int n = 0;

    @Override
    public void put(Key key, Value val) {
        if (isEmpty()) {
            n++;
            first = new SimpleNode<>(key, val);
            return;
        }

        SimpleNode<Key, Value> tmp = first;
        while (true) {
            if (tmp.key.compareTo(key) == 0) {
                tmp.value = val;
                return;
            }

            if (tmp.next == null) {
                n++;
                tmp.next = new SimpleNode<>(key, val);
                return;
            }

            tmp = tmp.next;
        }

    }

    @Override
    public Value get(Key key) {
        SimpleNode<Key, Value> tmp = first;
        while (tmp != null) {
            if (tmp.key.compareTo(key) == 0)
                return tmp.value;
            tmp = tmp.next;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        if (isEmpty())
            return;

        SimpleNode<Key, Value> prev = first;
        SimpleNode<Key, Value> curr = first.next;
        if (prev.key.compareTo(key) == 0) {
            first.next = null;
            first = curr;
            n--;
        }

        while (curr != null) {
            if (curr.key.compareTo(key) == 0) {
                prev.next = curr.next;
                n--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    @Override
    public boolean contains(Key key) {
        SimpleNode<Key, Value> tmp = first;
        while (tmp != null) {
            if (tmp.key.compareTo(key) == 0)
                return true;
            tmp = tmp.next;
        }
        return false;
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
            SimpleNode<Key, Value> tmp = first;

            @Override
            public boolean hasNext() {
                return tmp != null;
            }

            @Override
            public Key next() {
                Key key = tmp.key;
                tmp = tmp.next;
                return key;
            }
        };
    }


    public static void main(String[] args) {
        SymbolTable<Integer, String> st = new OrderedSequentialSearchST<>();
        SymbolTableTest.testST(st);
    }
}
