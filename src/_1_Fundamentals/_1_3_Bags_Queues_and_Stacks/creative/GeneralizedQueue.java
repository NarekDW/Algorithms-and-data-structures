package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

/*****************************************************************************************************
 * <p>
 * 1.3.38 Delete k th element. Implement a class that supports the following API:
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
public class GeneralizedQueue<T> {
    private T[] q;
    private int n;

    public GeneralizedQueue() {
        //noinspection unchecked
        q = (T[]) new Object[2];
        n = 0;
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
        q[n++] = item;
    }

    public T delete(int k) {
        if (n == 0 || k > n)
            return null;
        if (n == q.length / 4)
            resize(q.length / 2);
        T item = q[k];
        for (int i = k; i < n - 1; i++)
            q[i] = q[i + 1];
        q[n - 1] = null;
        n--;
        return item;
    }

    private void resize(int max) {
        //noinspection unchecked
        T[] tmp = (T[]) new Object[max];
        //noinspection ManualArrayCopy
        for (int i = 0; i < n; i++)
            tmp[i] = q[i];
        q = tmp;
    }

    public static void main(String[] args) {
        System.out.println("Running test for GeneralizedQueue.");
        GeneralizedQueue<Integer> queue = generateQueue(15);
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
    private static GeneralizedQueue<Integer> generateQueue(int n) {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<>();
        for (int i = 0; i < n; i++)
            queue.insert(i);
        return queue;
    }
}
