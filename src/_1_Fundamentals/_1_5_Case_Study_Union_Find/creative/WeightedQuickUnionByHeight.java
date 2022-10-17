package _1_Fundamentals._1_5_Case_Study_Union_Find.creative;

import _1_Fundamentals._1_5_Case_Study_Union_Find.Data;
import common.In;

import java.util.Arrays;

/*****************************************************************************************************
 * <p>
 * 1.5.14 Weighted quick-union by height. Develop a UF implementation that uses the
 * same basic strategy as weighted quick-union but keeps track of tree height and always
 * links the shorter tree to the taller one. Prove a logarithmic upper bound on the height
 * of the trees for N sites with your algorithm.
 *
 ****************************************************************************************************/
public class WeightedQuickUnionByHeight {
    private final int[] id;
    private final int[] height;
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
            id[rootP] = rootQ;
            height[rootQ]++;
        }

        count--;
    }


    // for N-1 tries we get (N-1)*(2*log(N) + 5) using the site-indexed id[] and height[] arrays
    public static void main(String[] args) {
        checkTreeHeight();
        checkTreeHeight(Data.LARGE_FILE_URL);
    }

    @SuppressWarnings("SameParameterValue")
    private static void checkTreeHeight(String fileUrl) {
        In in = new In(fileUrl);
        int n = in.readInt();
        WeightedQuickUnionByHeight weighted = new WeightedQuickUnionByHeight(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            weighted.union(p, q);
        }
        in.close();

        //noinspection OptionalGetWithoutIsPresent
        System.out.println("For n = " + n + ", tree height = " + Arrays.stream(weighted.height).max().getAsInt());
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
    private static void checkTreeHeight() {
        WeightedQuickUnionByHeight wqu = new WeightedQuickUnionByHeight(15);
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
        for (int i = 0; i < wqu.height.length; i++) {
            System.out.print(i + ":" + wqu.height[i] + "  ");
        }

        // For n = 15, tree height = 3
        //noinspection OptionalGetWithoutIsPresent
        System.out.println("\nHeight: " + Arrays.stream(wqu.height).max().getAsInt());
    }

}
