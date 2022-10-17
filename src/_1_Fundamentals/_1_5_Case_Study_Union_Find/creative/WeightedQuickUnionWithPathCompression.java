package _1_Fundamentals._1_5_Case_Study_Union_Find.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.StopwatchCPU;
import _1_Fundamentals._1_5_Case_Study_Union_Find.Data;
import _1_Fundamentals._1_5_Case_Study_Union_Find.UFQuickUnionWeighted;
import common.In;

/*****************************************************************************************************
 * <p>
 * 1.5.13 Weighted quick-union with path compression. Modify weighted quick-union
 * (Algorithm 1.5) to implement path compression, as described in Exercise 1.5.12.
 * Give a sequence of input pairs that causes this method to produce a tree of height 4.
 * Note : The amortized cost per operation for this algorithm is known to be bounded by a
 * function known as the inverse Ackermann function and is less than 5 for any conceivable
 * practical value of N.
 *
 ****************************************************************************************************/
public class WeightedQuickUnionWithPathCompression {

    private final int[] id;
    private final int[] sz; // size of component for roots (site indexed)
    private int count;
    private int numberOfConnections;

    public WeightedQuickUnionWithPathCompression(int n) {
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }

    @SuppressWarnings("unused")
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int arrayAccess = 0;

    // worst case log(N) array access
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
            arrayAccess += 2;
        }

        while (p != root) {
            if (sz[p] != 1)
                sz[p] -= 1;
            int next = id[p];
            id[p] = root;
            p = next;
        }

        arrayAccess++;
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        if (sz[rootQ] < sz[rootP]) {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            arrayAccess += 3;
        } else {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
            arrayAccess += 5;
        }

        numberOfConnections++;
        count--;
    }

    @SuppressWarnings("unused")
    public void unionReversed(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (sz[rootP] > sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
            arrayAccess += 5;
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            arrayAccess += 3;
        }
        count--;
    }

    // for N-1 tries we get (N-1)*(2*log(N) + 5) using the site-indexed id[] and sz[] arrays
    public static void main(String[] args) {
        checkPerformance();
        checkPathCompression();
    }

    /**
     * This should build a tree like this:
     *                      11
     *             /        \   \    \   \
     *            4          7   12  13  14
     *           / \ \ \    / \  \
     *          1   3 4 6  8   9  10
     *         / \
     *        0   2
     */
    private static void checkPathCompression() {
        WeightedQuickUnionWithPathCompression wqu = new WeightedQuickUnionWithPathCompression(15);
        wqu.union(0, 1);
        wqu.union(1, 2);
        wqu.union(3, 4);
        wqu.union(5, 3);
        wqu.union(4, 6);
        wqu.union(0, 4);

        wqu.union(8, 7);
        wqu.union(9, 7);
        wqu.union(10, 7);

        wqu.union(12, 11);
        wqu.union(13, 11);
        wqu.union(14, 11);

        wqu.union(7, 11);

        wqu.union(0, 8);

        for (int i = 0; i < wqu.id.length; i++) {
            System.out.print(i + ":" + wqu.id[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < wqu.sz.length; i++) {
            System.out.print(i + ":" + wqu.sz[i] + "  ");
        }

        System.out.println("\nArray access: " + wqu.arrayAccess);
    }

    private static void checkPerformance() {
        In in = new In(Data.LARGE_FILE_URL);
        int n = in.readInt();
        StopwatchCPU stopwatch = new StopwatchCPU();
        UFQuickUnionWeighted weighted = new UFQuickUnionWeighted(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            weighted.union(p, q);
        }
        double time = stopwatch.elapsedTime();
        System.out.println("Time: " + time);
        System.out.println("Count: " + weighted.count());
        in.close();

        In in2 = new In(Data.LARGE_FILE_URL);
        int n2 = in2.readInt();
        StopwatchCPU stopwatch2 = new StopwatchCPU();
        WeightedQuickUnionWithPathCompression weighted2 = new WeightedQuickUnionWithPathCompression(n2);
        while (!in2.isEmpty()) {
            int p = in2.readInt();
            int q = in2.readInt();
            weighted2.union(p, q);
        }
        double time2 = stopwatch2.elapsedTime();
        System.out.println("Time 2: " + time2);
        System.out.println("Count 2: " + weighted2.count());
        in2.close();
    }
}
