package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

/*****************************************************************************************************
 * <p>
 * 1.3.29 Write a Queue implementation that uses a circular linked list, which is the same
 * as a linked list except that no links are null and the value of last.next is first
 * whenever the list is not empty. Keep only one Node instance variable ( last ).
 *
 ****************************************************************************************************/
public class QueueCircled<Item> {

    private Node last;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    void enqueue(Item item) {
        if (n == 0) {
            last = new Node();
            last.item = item;
        } else if (n == 1) {
            Node first = last;
            last = new Node();
            last.item = item;
            first.next = last;
            last.next = first;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = oldLast.next;
            oldLast.next = last;
        }

        n++;
    }

    Item dequeue() {
        if (isEmpty()) {
            last = null;
            return null;
        }

        Item item;
        if (n != 1) {
            item = last.next.item;
            last.next = last.next.next;
        } else {
            item = last.item;
        }

        n--;
        return item;
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }


    public static void main(String[] args) {
        QueueCircled<Integer> queue = new QueueCircled<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(6);
        System.out.println(queue.dequeue());
    }
}
