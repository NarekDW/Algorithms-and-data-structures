package _1_Fundamentals._1_5_Case_Study_Union_Find;

import common.StopwatchCPU;
import common.In;

public class UFQuickUnionWeighted {

    private final int[] id;
    private final int[] sz; // size of component for roots (site indexed)
    private int count;

    public UFQuickUnionWeighted(int n) {
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

    @SuppressWarnings("unused")
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int arrayAccess = 0;

    // worst case log(N) array access
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
            arrayAccess += 2;
        }
        arrayAccess++;
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (sz[rootP] < sz[rootQ]) {
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

    /***
     * 1.5.11 Implement weighted quick-find, where you always change the id[] entries of the smaller component to the
     * identifier of the larger component. How does this change affect performance?
     */
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
        double originalTime = checkOriginalPerformance();
        double reversedTime = checkReversedPerformance();
        System.out.println(reversedTime);
        System.out.println("originalTime: " + originalTime + ", reversedTime: " + reversedTime);
        System.out.println(reversedTime / originalTime);
    }

    private static double checkOriginalPerformance() {
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
        in.close();

        System.out.println("Array access: " + weighted.arrayAccess);
        return time;
    }

    private static double checkReversedPerformance() {
        In in = new In(Data.LARGE_FILE_URL);
        int n = in.readInt();
        System.out.println(n);
        StopwatchCPU stopwatch = new StopwatchCPU();
        UFQuickUnionWeighted weighted = new UFQuickUnionWeighted(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            weighted.unionReversed(p, q);
        }
        double time = stopwatch.elapsedTime();
        in.close();
        return time;
    }
}
