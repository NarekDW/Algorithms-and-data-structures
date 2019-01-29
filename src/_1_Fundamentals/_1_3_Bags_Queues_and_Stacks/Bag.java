package _1_Fundamentals._1_3_Bags_Queues_and_Stacks;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private Node current;
    private int n;

    void add(Item item) {
        Node previous = current;
        current = new Node();
        current.item = item;
        current.next = previous;
        n++;
    }

    boolean isEmpty() {
        return current == null;
    }

    int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class BagIterator implements Iterator<Item> {

        private Node iter = current;

        @Override
        public boolean hasNext() {
            return iter != null;
        }

        @Override
        public Item next() {
            Item item = iter.item;
            iter = iter.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(5);

        for (Integer i : bag) {
            System.out.println(i);
        }
    }
}
