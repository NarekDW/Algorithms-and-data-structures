package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

/*****************************************************************************************************
 *
 * 1.3.32 Steque. A stack-ended queue or steque is a data type that supports push, pop, and
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
        T item;
        Node next;
        Node prev;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Steque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());

        System.out.println();

        Queue<Integer> queue = new Steque<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }
}
