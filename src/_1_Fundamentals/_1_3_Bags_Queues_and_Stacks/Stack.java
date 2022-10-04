package _1_Fundamentals._1_3_Bags_Queues_and_Stacks;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int n;
    private int pushCount;
    private int popCount;

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        pushCount++;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        popCount++;
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        return first.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public static <T> Stack<T> copy(Stack<T> stack) {
        Stack<T> copy = new Stack<>();
        Stack<T> tmp = new Stack<>();
        for (T t : stack)
            tmp.push(t);
        for (T t : tmp)
            copy.push(t);

        return copy;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.50 Fail-fast iterator.
     * Modify the iterator code in Stack to immediately throw a java.util.ConcurrentModificationException
     * if the client modifies the collection (via push() or pop()) during iteration? b).
     * Solution: Maintain a counter that counts the number of push() and pop() operations.
     * When creating an iterator, store this value as an Iterator instance variable.
     * Before each call to hasNext() and next(), check that this value has not changed since
     * construction of the iterator; if it has, throw the exception.
     *
     ****************************************************************************************************/
    private class StackIterator implements Iterator<Item> {

        private Node iterFirst = first;
        private final int pushC = pushCount;
        private final int popC = popCount;

        @Override
        public boolean hasNext() {
            return iterFirst != null;
        }

        @Override
        public Item next() {
            if (popC != popCount || pushC != pushCount)
                throw new ConcurrentModificationException();
            Item item = iterFirst.item;
            iterFirst = iterFirst.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= 5; i++)
            stack.push(i);

        for (Integer integer : stack)
            System.out.println("origin: " + integer);

        Stack<Integer> copy = Stack.copy(stack);
        for (Integer integer : copy)
            System.out.println("copy: " + integer);
    }
}
