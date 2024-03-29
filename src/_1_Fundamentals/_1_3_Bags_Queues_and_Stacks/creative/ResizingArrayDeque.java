package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

import java.util.Iterator;

/*****************************************************************************************************
 *
 * 1.3.33 Deque(ResizingArrayDeque).
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class ResizingArrayDeque<T> implements Iterable<T> {

    private T[] q;
    private int n;
    private int first;
    private int last;

    public ResizingArrayDeque() {
        q = (T[]) new Object[2];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == q.length;
    }

    public int size() {
        return n;
    }

    public void pushLeft(T t) {
        if (isFull()) resize(n * 2);
        first--;
        if (first < 0) first = q.length - 1;
        q[first] = t;
        n++;
    }

    public void pushRight(T t) {
        if (isFull()) resize(n * 2);
        q[last++] = t;
        if (last == q.length) last = 0;
        n++;
    }

    public T popLeft() {
        if (n == 0)
            return null;
        if (n <= q.length / 4) resize(q.length / 2);
        T t = q[first];
        q[first] = null;
        first++;
        if (first == q.length) first = 0;
        n--;
        return t;
    }

    public T popRight() {
        if (n == 0)
            return null;
        if (n <= q.length / 4) resize(q.length / 2);
        last--;
        if (last < 0) last = q.length - 1;
        T t = q[last];
        q[last] = null;
        n--;
        return t;
    }

    private void resize(int max) {
        T[] tmp = (T[]) new Object[max];
        for (int i = 0; i < n; i++)
            tmp[i] = q[(i + first) % q.length];
        first = 0;
        last = n;
        q = tmp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int x = first;

            @Override
            public boolean hasNext() {
                return x - last != 0;
            }

            @Override
            public T next() {
                T t = q[x];
                x++;
                if (x == q.length) x = 0;
                return t;
            }
        };
    }

    public static void main(String[] args) {
        System.out.println("Running test for ResizingArrayDeque.");
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>();
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushLeft(3);

        if (deque.size() != 3)
            throw new RuntimeException();

        if (deque.popLeft() != 3)
            throw new RuntimeException();
        if (deque.popRight() != 1)
            throw new RuntimeException();
        if (deque.popLeft() != 2)
            throw new RuntimeException();

        if (deque.popLeft() != null)
            throw new RuntimeException();
        if (deque.popRight() != null)
            throw new RuntimeException();

        deque.pushRight(11);
        deque.pushRight(22);
        deque.pushRight(33);

        if (deque.popLeft() != 11)
            throw new RuntimeException();
        if (deque.popRight() != 33)
            throw new RuntimeException();
        if (deque.popLeft() != 22)
            throw new RuntimeException();

        if (deque.popLeft() != null)
            throw new RuntimeException();
        if (deque.popRight() != null)
            throw new RuntimeException();
    }
}
