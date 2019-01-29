package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

/*****************************************************************************************************
 *
 * 1.3.38 Delete k th element. Implement a class that supports the following API:
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
public class GeneralizedQueue<T> {
    private T[] q;
    private int n;
    private int first;
    private int last;

    public GeneralizedQueue() {
        q = (T[]) new Object[2];
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

    public void insert(T item) {
        if (n == q.length)
            resize(n * 2);
        q[last++] = item;
        if (last == q.length)
            last = 0;
        n++;
    }

    public T delete(int k) {
        if (n == q.length / 4) resize(q.length / 2);
        T item = q[k];
        for (int i = k; i < n - 1; i++)
            q[i] = q[i + 1];
        q[n - 1] = null;
        n--;
        return item;
    }

    private void resize(int max) {
        T[] tmp = (T[]) new Object[max];
        for (int i = 0; i < n; i++)
            tmp[i] = q[(first + i) % q.length];
        first = 0;
        last = n;
        q = tmp;
    }

    public static void main(String[] args) {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<>();
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.print();
        System.out.println();
        System.out.println(queue.delete(1));
        System.out.println(queue.delete(1));

        queue.print();

    }

    private void print() {
        for (T t : q) {
            System.out.print(t + " ");
        }
    }
}
