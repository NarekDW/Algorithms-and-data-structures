package _1_Fundamentals._1_5_Case_Study_Union_Find.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import common.In;

/*****************************************************************************************************
 *
 * 1.5.13 Weighted quick-union with path compression. Modify weighted quick-union
 * (Algorithm 1.5) to implement path compression, as described in Exercise 1.5.12.
 * Give a sequence of input pairs that causes this method to produce a tree of height 4.
 * Note : The amortized cost per operation for this algorithm is known to be bounded by a
 * function known as the inverse Ackermann function and is less than 5 for any conceivable
 * practical value of N.
 *
 ****************************************************************************************************/
public class WeightedQuickUnionWithPathCompression {

    private int[] id;
    private int[] sz; // size of component for roots (site indexed)
    private int count;

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

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int arrayAccess = 0;

    // worst case log(N) array access
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
            arrayAccess += 2;
        }

        while (p != root) {
            p = id[p];
            id[p] = root;
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
        _1_Fundamentals._1_5_Case_Study_Union_Find.UFQuickUnionWeighted weighted2 = new _1_Fundamentals._1_5_Case_Study_Union_Find.UFQuickUnionWeighted(n2);
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
