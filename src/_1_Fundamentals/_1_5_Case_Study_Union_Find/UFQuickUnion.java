package _1_Fundamentals._1_5_Case_Study_Union_Find;

import common.In;

public class UFQuickUnion {

    private int[] id;
    private int count;

    public UFQuickUnion(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int arrayAccess = 0;

    // best case 1 array access
    // worst case 2N - 1 array access
    // (this count is conservative since compiled code will typically not do an array access for the
    //  second reference to id[p] in the while loop)
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
            arrayAccess += 2;
        }

        // 1.5.12 Quick-union with path compression.
        while (p != id[p]) {
            p = id[p];
            id[p] = root;
            arrayAccess += 3;
        }

        arrayAccess += 2;
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        id[rootP] = rootQ;
        arrayAccess++;
        count--;
//        System.out.println(arrayAccess);
        arrayAccess = 0;
    }

    public static void main(String[] args) {
//        In in = new In("tinyUF.txt");
//        In in = new In("mediumUF.txt");
        In in = new In("largeUF.txt");
//        In in = new In("testUF.txt");
        int n = in.readInt();
        UFQuickUnion ufQuickUnion = new UFQuickUnion(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            ufQuickUnion.union(p, q);
        }
        System.out.println("Count = " + ufQuickUnion.count());
    }
}
