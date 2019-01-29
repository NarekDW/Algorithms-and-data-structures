package _1_Fundamentals._1_3_Bags_Queues_and_Stacks;

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
        n = 0;
    }

    public void push(Item item) {
        if (n == a.length)
            resize(a.length * 2);
        a[n++] = item;
    }

    public Item pop() {
        Item item = a[--n];
        a[n] = null;
        if (n == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    boolean isEmpty() {
        return n == 0;
    }

    boolean isFull() {
        return n == a.length;
    }

    int size() {
        return n;
    }

    private void resize(int max) {
        System.out.println("resize(int " + max + ")");
        Item[] tmp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++)
            tmp[i] = a[i];
        a = tmp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int x = n;

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Item next() {
            return a[--x];
        }
    }

    public static void main(String[] args) {
        FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(5);
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(stack.pop());
        }

        stack.forEach(integer -> {});
        for (Integer integer : stack) {
            // iterator
        }

    }
}
