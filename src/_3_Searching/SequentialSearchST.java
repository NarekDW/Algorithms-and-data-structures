package _3_Searching;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> {
    private int n;
    private Node first;
    private Value lastValue;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                Value value = x.value;
                lastValue = value;
                return value;
            }
        return null;
    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }

        first = new Node(key, value, first);
        n++;
    }

    public int size() {
        return n;
    }

    public Iterable<Key> keys() {
        return () -> new Iterator<Key>() {
            Node x = first;

            @Override
            public boolean hasNext() {
                return x.next != null;
            }

            @Override
            public Key next() {
                Key key = x.key;
                x = x.next;
                return key;
            }
        };
    }

    public void delete2(Key key) {
        Node prev = null;
        for (Node x = first; x != null; prev = x, x = x.next)
            if (key.equals(x.key)) {
                if (prev != null)
                    prev.next = x.next;
                x = null;
                n--;
            }
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    // Recursive solution
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }

        x.next = delete(x.next, key);
        return x;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        Value value = get(key);
        if (value != null)
            lastValue = value;
        return value != null;
    }

    public Value getLastValue() {
        return lastValue;
    }
}
