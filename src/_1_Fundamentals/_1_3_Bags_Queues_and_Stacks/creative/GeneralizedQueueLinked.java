package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;


/*****************************************************************************************************
 *
 * 1.3.38 (GeneralizedQueueLinked) Delete k th element. Implement a class that supports the following API:
 *
 * public class GeneralizedQueue<Item>
 *      GeneralizedQueue() - create an empty queue
 *      boolean isEmpty() - is the queue empty?
 *      void insert(Item x) add an item
 *      Item delete(int k) delete and return the k th least recently inserted item
 *
 * First, develop an implementation that uses an array implementation, and then develop
 * one that uses a linked-list implementation. Note : the algorithms and data structures
 * that we introduce in Chapter 3 make it possible to develop an implementation that
 * can guarantee that both insert() and delete() take time prortional to the logarithm
 * of the number of items in the queue—see Exercise 3.5.27.
 *
 ****************************************************************************************************/
public class GeneralizedQueueLinked<T> {

    private Node left;
    private Node right;
    private int n;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(T item) {
        Node oldRight = right;
        right = new Node();
        right.item = item;
        right.prev = oldRight;
        if (isEmpty()) left = right;
        else oldRight.next = right;
        n++;
    }

    public T delete(int k) {
        Node tmp = left;
        while (k != 0) {
            tmp = tmp.next;
            k--;
        }

        Node prev = tmp.prev;
        Node next = tmp.next;
        prev.next = next;
        next.prev = prev;
        tmp.prev = null;
        tmp.next = null;

        return tmp.item;
    }

    private class Node {
        T item;
        Node next;
        Node prev;

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GeneralizedQueueLinked{" +
                "left=" + left +
                '}';
    }

    public static void main(String[] args) {
        GeneralizedQueueLinked<Integer> queue = new GeneralizedQueueLinked<>();
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        System.out.println(queue);

        System.out.println(queue.delete(1));
        System.out.println(queue.delete(1));

        System.out.println(queue);
    }

}
