package _1_Fundamentals._1_5_Case_Study_Union_Find;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import common.In;

public class UFQuickUnionWeighted {

    private int[] id;
    private int[] sz; // size of component for roots (site indexed)
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

//        System.out.println(arrayAccess);
        arrayAccess = 0;

        count--;
    }

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
//        In in = new In("largeUF.txt");
////        In in = new In("testUF.txt");
//        int n = in.readInt();
//        Stopwatch stopwatch = new Stopwatch();
//        UFQuickUnionWeighted weighted = new UFQuickUnionWeighted(n);
//        while (!in.isEmpty()) {
//            int p = in.readInt();
//            int q = in.readInt();
//            weighted.unionReversed(p, q);
//        }
//        double v = stopwatch.elapsedTime();
//        System.out.println("rev = " + v);
//        System.out.println("Count = " + weighted.count());


        In in2 = new In("largeUF.txt");
        int n2 = in2.readInt();
        Stopwatch stopwatch2 = new Stopwatch();
        UFQuickUnionWeighted weighted2 = new UFQuickUnionWeighted(n2);
        while (!in2.isEmpty()) {
            int p = in2.readInt();
            int q = in2.readInt();
            weighted2.union(p, q);
        }
        double v2 = stopwatch2.elapsedTime();
        System.out.println("usu = " + v2);
        System.out.println("Count = " + weighted2.count());

    }
}
