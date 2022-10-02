package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

/*****************************************************************************************************
 * <p>
 * 1.3.32 Steque.
 * A stack-ended queue or steque is a data type that supports push, pop, and
 * enqueue. Articulate an API for this ADT. Develop a linked-list-based implementation.
 *
 ****************************************************************************************************/
public class Steque<T> implements Stack<T>, Queue<T> {

    private Node first;
    private Node last;
    private int n;

    @Override
    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.item = t;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        n++;
    }

    @Override
    public T dequeue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        n--;
        return item;
    }

    @Override
    public void push(T t) {
        enqueue(t);
    }

    @Override
    public T pop() {
        T item = last.item;
        Node prev = last.prev;
        last.prev = null;
        last = prev;
        n--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    private class Node {
        private T item;
        private Node next;
        private Node prev;
    }


    public static void main(String[] args) {
        testStack();
        testQueue();
    }

    private static void testStack() {
        System.out.println("Running test for Stack.");
        Stack<Integer> stack = new Steque<>();
        for (int i = 1; i <= 5; i++)
            stack.push(i);

        if (stack.size() != 5)
            throw new RuntimeException();

        for (int i = 5; i >= 1; i--)
            if (stack.pop() != i)
                throw new RuntimeException();

        if (!stack.isEmpty())
            throw new RuntimeException();
    }

    private static void testQueue() {
        System.out.println("Running test for Queue.");
        Queue<Integer> queue = new Steque<>();
        for (int i = 1; i <= 5; i++)
            queue.enqueue(i);

        if (queue.size() != 5)
            throw new RuntimeException();

        for (int i = 1; i <= 5; i++)
            if (queue.dequeue() != i)
                throw new RuntimeException();

        if (!queue.isEmpty())
            throw new RuntimeException();
    }
}
