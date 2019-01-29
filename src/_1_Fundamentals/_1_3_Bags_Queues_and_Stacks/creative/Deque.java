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
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
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
        T item = left.item;
        left = left.next;
        n--;
        if (isEmpty()) right = null;
        return item;
    }

    public T popRight() {
        T item = right.item;
        right = right.prev;
        n--;
        if (isEmpty()) left = null;
        return item;
    }

    @Override
    public String toString() {
        return "Deque{" +
                "left=" + left +
                '}';
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3);

        for (Integer i : deque) {
            System.out.println("it: " + i);
        }

        System.out.println(deque.popLeft());
        System.out.println(deque.popLeft());
        System.out.println(deque.popLeft());
        System.out.println(deque);

        Deque<Integer> deque2 = new Deque<>();
        deque2.pushRight(1);
        deque2.pushRight(2);
        deque2.pushRight(3);

        System.out.println(deque2.popRight());
        System.out.println(deque2.popRight());
        System.out.println(deque2.popRight());
        System.out.println(deque2);
    }
}
