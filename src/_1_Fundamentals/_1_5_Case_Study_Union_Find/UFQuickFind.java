package _1_Fundamentals._1_5_Case_Study_Union_Find;

import common.In;

public class UFQuickFind {

    private final int[] id;
    private int count;

    public UFQuickFind(int n) {
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

    public int find(int p) {
        return id[p];
    }

    // from N + 2 to 2N
    public void union(int p, int q) {
        int idP = find(p);
        int idQ = find(q);
        int arrayAccess = 2;
        if (idP == idQ) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == idP) {
                id[i] = idQ;
                // Here should be +2
                arrayAccess++;
            }

            arrayAccess++;
        }
        System.out.println("ArrayAccess = " + arrayAccess);
        count--;
    }

    // for N-1 tries we get ~ N^2 using the site-indexed id[] array
    public static void main(String[] args) {
        In in = new In(Data.MEDIUM_FILE_URL);
        int n = in.readInt();
        UFQuickFind ufQuickFind = new UFQuickFind(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            ufQuickFind.union(p, q);
        }
        System.out.println("Count = " + ufQuickFind.count());
        in.close();
    }
}
