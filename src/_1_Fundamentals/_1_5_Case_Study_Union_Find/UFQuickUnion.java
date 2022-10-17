package _1_Fundamentals._1_5_Case_Study_Union_Find;

import common.In;

import java.util.Arrays;

public class UFQuickUnion {

    private final int[] id;
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

    @SuppressWarnings("unused")
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
            int next = id[p];
            id[p] = root;
            p = next;
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
    }

    public static void main(String[] args) {
        testOnFile(Data.LARGE_FILE_URL);
        pathOfLengthMoreThen4();
    }

    @SuppressWarnings("SameParameterValue")
    private static void testOnFile(String file) {
        In in = new In(file);
        int n = in.readInt();
        UFQuickUnion ufQuickUnion = new UFQuickUnion(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            ufQuickUnion.union(p, q);
        }
        System.out.println("Count = " + ufQuickUnion.count());
        in.close();
    }

    private static void pathOfLengthMoreThen4() {
        // Give a sequence of input pairs that causes this method to produce a path of length 4
        // 0 - 1, 1 - 2, 2 - 3, 3 - 4
        UFQuickUnion ufQuickUnion = new UFQuickUnion(5);
        ufQuickUnion.union(0, 1);
        ufQuickUnion.union(1, 2);
        ufQuickUnion.union(2, 3);
        ufQuickUnion.union(3, 4);

        System.out.println(Arrays.toString(ufQuickUnion.id));
    }
}
