package _2_Sorting._2_5_Sorting_Applications.creative;

import common.StdOut;

/*****************************************************************************************************
 *
 * 2.5.24 Stable priority queue. Develop a stable priority-queue implementation (which
 * returns duplicate keys in the same order in which they were inserted).
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class StablePriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n = 0;
    private int[] time;
    private int timestamp = 1;

    public StablePriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        time = new int[capacity + 1];
    }

    public void insert(Key v) {
        pq[++n] = v;
        time[n] = timestamp++;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        time[n + 1] = 0;
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && less(j, j + 1)) j++;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        int c = pq[i].compareTo(pq[j]);
        if (c < 0) return true;
        if (c > 0) return false;
        return time[i] < time[j];
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        int timeTmp = time[i];
        time[i] = time[j];
        time[j] = timeTmp;
    }

    public boolean isEmpty() {
        return n == 0;
    }


    // Test
    private static final class Tuple implements Comparable<Tuple> {
        private String name;
        private int id;

        private Tuple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int compareTo(Tuple that) {
            return this.name.compareTo(that.name);
        }

        public String toString() {
            return name + " " + id;
        }
    }

    // test client
    public static void main(String[] args) {

        // insert a bunch of strings
        String text = "it was the best of times it was the worst of times it was the "
                + "age of wisdom it was the age of foolishness it was the epoch "
                + "belief it was the epoch of incredulity it was the season of light "
                + "it was the season of darkness it was the spring of hope it was the "
                + "winter of despair";
        String[] strings = text.split(" ");

        StablePriorityQueue<Tuple> pq = new StablePriorityQueue<Tuple>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            pq.insert(new Tuple(strings[i], i));
        }


        // delete and print each key
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
        StdOut.println();

    }

}
