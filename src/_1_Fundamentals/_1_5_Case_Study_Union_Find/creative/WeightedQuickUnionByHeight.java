package _1_Fundamentals._1_5_Case_Study_Union_Find.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _1_Fundamentals._1_5_Case_Study_Union_Find.UFQuickUnionWeighted;
import common.In;

/*****************************************************************************************************
 *
 * 1.5.14 Weighted quick-union by height. Develop a UF implementation that uses the
 * same basic strategy as weighted quick-union but keeps track of tree height and always
 * links the shorter tree to the taller one. Prove a logarithmic upper bound on the height
 * of the trees for N sites with your algorithm.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class WeightedQuickUnionByHeight {

    private int[] id;
    private int[] height; // size of component for roots (site indexed)
    private int count;

    public WeightedQuickUnionByHeight(int n) {
        count = n;
        id = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            height[i] = 0;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (height[rootP] < height[rootQ])
            id[rootP] = rootQ;
        else if (height[rootP] > height[rootQ])
            id[rootQ] = rootP;
        else {
            id[rootQ] = rootP;
            height[rootP]++;
        }

        count--;
    }

    public void unionReversed(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (height[rootP] > height[rootQ]) {
            id[rootP] = rootQ;
            height[rootQ] += height[rootP];
        } else {
            id[rootQ] = rootP;
            height[rootP] += height[rootQ];
        }
        count--;
    }

    // for N-1 tries we get (N-1)*(2*log(N) + 5) using the site-indexed id[] and height[] arrays
    public static void main(String[] args) {
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
