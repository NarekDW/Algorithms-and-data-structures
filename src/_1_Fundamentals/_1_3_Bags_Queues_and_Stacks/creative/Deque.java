package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

import java.util.Iterator;

/*****************************************************************************************************
 *
 * 1.3.33 Deque. A double-ended queue or deque (pronounced “deck”) is like a stack or
 * a queue but supports adding and removing items at both ends. A deque stores a collec-
 * tion of items and supports the following API:
 *
 * public class Deque<Item> implements Iterable<Item>
 *      Deque()  - create an empty deque
 *      boolean isEmpty()  - is the deque empty?
 *      int size()  - number of items in the deque
 *      void pushLeft(Item item)  - add an item to the left end
 *      void pushRight(Item item)  - add an item to the right end
 *      Item popLeft()  - remove an item from the left end
 *      Item popRight()  - remove an item from the right end
 *
 * API for a generic double-ended queue
 * Write a class Deque that uses a doubly-linked list to implement this API and a class
 * ResizingArrayDeque that uses a resizing array.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class Deque<T> implements Iterable<T> {

    private Node left;
    private Node right;
    private int n;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node x = left;

            @Override
            public boolean hasNext() {
                return x != null;
            }

            @Override
            public T next() {
                T item = x.item;
                x = x.next;
                return item;
            }
        };
    }

    private class Node {
        T item;
        Node next;
        Node prev;

        @Override
        public String toString() {
            return "Node{item=" + item + ", next=" + next + '}';
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void pushLeft(T t) {
        Node oldLeft = left;
        left = new Node();
        left.item = t;
        left.next = oldLeft;
        if (isEmpty()) right = left;
        else oldLeft.prev = left;
        n++;
    }

    public void pushRight(T t) {
        Node oldRight = right;
        right = new Node();
        right.item = t;
        right.prev = oldRight;
        if (isEmpty()) left = right;
        else oldRight.next = right;
        n++;
    }

    public T popLeft() {
        if (isEmpty()) {
            right = null;
            left = null;
            return null;
        }
        T item = left.item;
        Node newLeft = left.next;
        if (newLeft != null)
            newLeft.prev = null;
        left.next = null;
        left = newLeft;
        n--;
        return item;
    }

    public T popRight() {
        if (isEmpty()) {
            right = null;
            left = null;
            return null;
        }
        T item = right.item;
        Node newRight = right.prev;
        if (newRight != null)
            newRight.next = null;
        right.prev = null;
        right = newRight;
        n--;
        return item;
    }

    @Override
    public String toString() {
        return "Deque{left=" + left + '}';
    }

    public static void main(String[] args) {
        System.out.println("Running test for Deque.");
        Deque<Integer> deque = new Deque<>();
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3);

        if (deque.size() != 3)
            throw new RuntimeException();

        if (deque.popLeft() != 3)
            throw new RuntimeException();
        if (deque.popRight() != 1)
            throw new RuntimeException();
        if (deque.popLeft() != 2)
            throw new RuntimeException();

        if (deque.popLeft() != null)
            throw new RuntimeException();
        if (deque.popRight() != null)
            throw new RuntimeException();

        deque.pushRight(11);
        deque.pushRight(22);
        deque.pushRight(33);

        if (deque.popLeft() != 11)
            throw new RuntimeException();
        if (deque.popRight() != 33)
            throw new RuntimeException();
        if (deque.popLeft() != 22)
            throw new RuntimeException();

        if (deque.popLeft() != null)
            throw new RuntimeException();
        if (deque.popRight() != null)
            throw new RuntimeException();
    }
}
