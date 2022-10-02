package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;


/*****************************************************************************************************
 * <p>
 * 1.3.38 (GeneralizedQueueLinked) Delete k th element. Implement a class that supports the following API:
 * <p>
 * public class GeneralizedQueue<Item>
 *      GeneralizedQueue() - create an empty queue
 *      boolean isEmpty() - is the queue empty?
 *      void insert(Item x) add an item
 *      Item delete(int k) delete and return the k th least recently inserted item
 * <p>
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
        if (n == 0 || k > n)
            return null;
        n--;
        Node tmp = left;
        // if we delete first element, we need to move left pointer.
        if (k == 0) {
            left = tmp.next;
            tmp.next = null;
            return tmp.item;
        }

        // if we delete last element, we need to move right pointer.
        if (k == n) {
            tmp = right;
            right = tmp.prev;
            tmp.prev = null;
            return tmp.item;
        }

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
            return "Node{item=" + item + ", next=" + next + '}';
        }
    }

    @Override
    public String toString() {
        return "GQL{left=" + left + '}';
    }

    public static void main(String[] args) {
        System.out.println("Running test for GeneralizedQueueLinked.");
        GeneralizedQueueLinked<Integer> queue = generateQueue(15);
        if (queue.isEmpty())
            throw new RuntimeException("isEmpty");

        for (int i = 0; i < 15; i++)
            if (queue.delete(0) != i)
                throw new RuntimeException(String.valueOf(i));

        if (!queue.isEmpty())
            throw new RuntimeException("!isEmpty");

        queue = generateQueue(15);
        if (queue.delete(5) != 5)
            throw new RuntimeException("delete != 6");
        if (queue.delete(11) != 12)
            throw new RuntimeException("delete != 12");
        if (queue.delete(12) != 14)
            throw new RuntimeException("delete != 14");
        queue.insert(33);
        if (queue.delete(12) != 33)
            throw new RuntimeException("delete != 33");
        if (queue.delete(100) != null)
            throw new RuntimeException("delete != null");
    }

    @SuppressWarnings("SameParameterValue")
    private static GeneralizedQueueLinked<Integer> generateQueue(int n) {
        GeneralizedQueueLinked<Integer> queue = new GeneralizedQueueLinked<>();
        for (int i = 0; i < n; i++)
            queue.insert(i);
        return queue;
    }
}
