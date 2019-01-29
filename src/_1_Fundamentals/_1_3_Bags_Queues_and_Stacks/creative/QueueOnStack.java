package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;

public class QueueOnStack<T> {

    private Stack<T> first = new Stack<>();
    private Stack<T> second = new Stack<>();
    private int n;

    public void enqueue(T item) {
        first.push(item);
        n++;
    }

    public T dequeue() {
        n--;
        if (!second.isEmpty())
            return second.pop();
        while (!first.isEmpty())
            second.push(first.pop());
        return second.pop();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        QueueOnStack<Integer> queue = new QueueOnStack<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
