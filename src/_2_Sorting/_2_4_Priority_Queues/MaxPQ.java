package _2_Sorting._2_4_Priority_Queues;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;       // heap-ordered complete binary tree
    private int N = 0;      // in pq[1..N] with pq[0] unused

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /*****************************************************************************************************
     *
     * 2.4.19 Implement the constructor for MaxPQ that takes an array of items as argument,
     * using the bottom-up heap construction method described on page 323 in the text.
     *
     ****************************************************************************************************/
    public MaxPQ(Key[] pq) {
        this.pq = pq;
        for (int i = 2; i < pq.length; i++)
            swim(i);
    }


    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
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
}
