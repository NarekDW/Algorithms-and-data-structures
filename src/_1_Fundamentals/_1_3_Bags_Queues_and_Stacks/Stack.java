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

    int size() {
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

    private class StackIterator implements Iterator<Item> {

        private Node iterFirst = first;
        private int pushC = pushCount;
        private int popC = popCount;

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
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        for (Integer integer : stack) {
            System.out.println("origin: " + integer);
        }

        Stack<Integer> copy = Stack.copy(stack);
        for (Integer integer : copy) {
            System.out.println("copy: " + integer);
        }
    }
}
