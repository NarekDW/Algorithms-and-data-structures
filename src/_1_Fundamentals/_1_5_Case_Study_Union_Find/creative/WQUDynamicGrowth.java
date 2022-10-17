package _1_Fundamentals._1_5_Case_Study_Union_Find.creative;

import _1_Fundamentals._1_5_Case_Study_Union_Find.Data;
import common.In;

/*****************************************************************************************************
 * <p>
 * 1.5.20 Dynamic growth. Using linked lists or a resizing array, develop a weighted quick-union implementation
 * that removes the restriction on needing the number of objects ahead of time.
 * Add a method newSite() to the API, which returns an int identifier.
 * <p>
 * This implementation is based on a resizing array.
 ****************************************************************************************************/
public class WQUDynamicGrowth {
    private int[] id;
    private int[] sz;
    private int size;

    public WQUDynamicGrowth() {
        size = 0;
        id = new int[size];
        sz = new int[size];
    }

    public int newSite() {
        for (int i = size - 1; i >= 0; i--) {
            if (id[i] > -1)
                return i + 1;

        }
        return 0;
    }

    public int find(int p) {
        if (p >= size)
            resizeArrays(p);
        if (id[p] == -1)
            id[p] = p;
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
    }

    private void resizeArrays(int p) {
        int newSize = Math.max(2 * size, p + 1);
        int[] tmpId = new int[newSize];
        int[] tmpSz = new int[newSize];
        for (int i = 0; i < size; i++) {
            tmpId[i] = id[i];
            tmpSz[i] = sz[i];
        }
        for (int i = size; i < newSize; i++) {
            if (i == p) {
                tmpId[i] = i;
                tmpSz[i] = 1;
                continue;
            }
            tmpId[i] = -1;
            tmpSz[i] = -1;
        }

        size = newSize;
        this.id = tmpId;
        this.sz = tmpSz;
    }


    public static void main(String[] args) {
        In in = new In(Data.MEDIUM_FILE_URL);
        int n = in.readInt();
        System.out.println("Original size: " + n);
        WQUDynamicGrowth wquDynamicGrowth = new WQUDynamicGrowth();
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            wquDynamicGrowth.union(p, q);
        }
        System.out.println("Result size: " + wquDynamicGrowth.newSite());
        in.close();
    }
}
