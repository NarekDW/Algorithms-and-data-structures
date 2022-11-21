package _2_Sorting._2_4_Priority_Queues;

import common.SortUtils;

/*****************************************************************************************************
 * <p>
 * 2.4.19 Implement the constructor for MaxPQ that takes an array of items as argument,
 * using the bottom-up heap construction method described on page 323 in the text.
 * <p>
 * 2.4.22 Array resizing. Add array resizing to MaxPQ, and prove bounds like those
 * of Proposition Q for array accesses, in an amortized sense.
 *
 ****************************************************************************************************/
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;       // heap-ordered complete binary tree
    private int n = 0;            // in pq[1..N] with pq[0] unused
    private static final int DEFAULT_SIZE = 2;

    public MaxPQ(int maxN) {
        //noinspection unchecked
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ() {
        this(DEFAULT_SIZE);
    }

    public MaxPQ(Key[] pq) {
        this.pq = pq;
        for (int i = 2; i < pq.length; i++)
            swim(i);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key v) {
        if (n + 2 > pq.length)
            resize(n * 2);

        pq[++n] = v;
        swim(n);
    }

    public Key delMax() {
        if (isEmpty())
            return null;

        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        sink(1);

        if (n < pq.length / 4)
            resize(n * 2);

        return max;
    }

    private void resize(int max) {
        //noinspection unchecked
        Key[] tmp = (Key[]) new Comparable[max + 1];
        //noinspection ManualArrayCopy
        for (int i = 0; i <= n; i++)
            tmp[i] = pq[i];
        pq = tmp;
    }


    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        Integer[] integers = SortUtils.generateArrayInteger(10_000);
        for (Integer i : integers)
            maxPQ.insert(i);

        Integer prev = maxPQ.delMax();
        while (!maxPQ.isEmpty()) {
            Integer curr = maxPQ.delMax();
            if (curr > prev)
                throw new RuntimeException();
            prev = curr;
        }
    }
}
