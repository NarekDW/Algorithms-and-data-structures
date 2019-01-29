package _1_Fundamentals._1_3_Bags_Queues_and_Stacks;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    public Queue() {
    }

    /*****************************************************************************************************
     *
     * 1.3.41 Copy a queue. Create a new constructor so that
     * Queue<Item> r = new Queue<Item>(q);
     * makes r a reference to a new and independent copy of the queue q . You should be able
     * to push and pop from either q or r without influencing the other. Hint : Delete all of the
     * elements from q and add these elements to both q and r .
     *
     ****************************************************************************************************/
    public Queue(Queue<Item> queue) {
        int size = queue.size();
        while (size != 0) {
            Item t = queue.dequeue();
            enqueue(t);
            queue.enqueue(t);
            size--;
        }

    }

    private class Node {
        Item item;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()){
            last = null;
            return null;
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node iterFirst = first;

        @Override
        public boolean hasNext() {
            return iterFirst != null;
        }

        @Override
        public Item next() {
            Item item = iterFirst.item;
            iterFirst = iterFirst.next;
            return item;
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "first=" + first +
                '}';
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue);

        Queue<Integer> queue2 = new Queue<>(queue);
        queue2.enqueue(6);
        System.out.println(queue2);

        queue.enqueue(55);
        System.out.println(queue);


//        for (Integer i : queue) {
//            System.out.println("it: " + i);
//        }
//
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());

    }
}
