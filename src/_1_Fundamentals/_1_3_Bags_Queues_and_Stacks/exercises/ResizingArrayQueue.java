package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n;
    private int first;
    private int last;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
       // -------------------------------------------------------------------------------------------------------------
        System.out.println("S max = " + max);
        for (Item i : q) {
            System.out.print(i + " ");
        }
        System.out.println("\nE");
        // -------------------------------------------------------------------------------------------------------------

        Item[] tmp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++)
            tmp[i] = q[(first + i) % q.length];
        first = 0;
        last = n;
        q = tmp;

        // -------------------------------------------------------------------------------------------------------------
        System.out.println("S2 max = " + max);
        for (Item i : q) {
            System.out.print(i + " ");
        }
        System.out.println("\nE2");
        // -------------------------------------------------------------------------------------------------------------
    }

    public void enqueue(Item item) {
        if (n == q.length)
            resize(n * 2);
        q[last++] = item;
        if (last == q.length)
            last = 0;
        n++;
    }

    public Item dequeue() {
        if (n == q.length / 4)
            resize(q.length / 2);
        Item item = q[first];
        q[first] = null;
        first++;
        if (first == q.length)
            first = 0;
        n--;
        return item;
    }

    public Item peek() {
        return q[first];
    }


    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            public Item next() {
                Item item = q[(i + first) % q.length];
                i++;
                return item;
            }
        };
    }


    public static void main(String[] args) {
        ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>();
        System.out.println("isEmpty: " + queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue.dequeue());

        queue.enqueue(6);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println(queue.dequeue());
        System.out.println("isEmpty: " + queue.isEmpty());


        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);

        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);


        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(18);
        queue.enqueue(19);
        queue.enqueue(20);
        queue.enqueue(21);
        queue.enqueue(22);
        queue.enqueue(23);
    }
}
