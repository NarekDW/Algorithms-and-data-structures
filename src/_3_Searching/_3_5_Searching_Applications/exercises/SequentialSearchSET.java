package _3_Searching._3_5_Searching_Applications.exercises;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 3.5.2 Develop a SET implementation SequentialSearchSET by starting with the code
 * for SequentialSearchST and eliminating all of the code involving values.
 *
 ****************************************************************************************************/
public class SequentialSearchSET<Key> {
    private int n;
    private Node first;

    private class Node {
        Key key;
        Node next;

        public Node(Key key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public void put(Key key) {
        if (!contains(key)) {
            first = new Node(key, first);
            n++;
        }
    }

    public int size() {
        return n;
    }

    public Iterable<Key> keys() {
        return () -> new Iterator<Key>() {
            Node x = first;

            @Override
            public boolean hasNext() {
                return x != null && x.next != null;
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

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        Node x = this.first;
        while (x != null) {
            if (x.key.equals(key))
                return true;
            x = x.next;
        }
        return false;
    }

    public static void main(String[] args) {
        SequentialSearchSET<Integer> set = new SequentialSearchSET<>();
        for (int i = 0; i < 10; i++)
            set.put(i);

        set.keys().forEach(System.out::println);

        for (int i = 0; i < 10; i++)
            set.delete(i);

        set.keys().forEach(System.out::println);

    }
}
