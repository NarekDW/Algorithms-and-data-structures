package _1_Fundamentals._1_5_Case_Study_Union_Find.creative;

import _1_Fundamentals._1_5_Case_Study_Union_Find.UFQuickUnion;
import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 1.5.17 Random connections.
 * Develop a UF client ErdosRenyi that takes an integer value N from the command line,
 * generates random pairs of integers between 0 and N-1, calling connected() to determine
 * if they are connected and then union() if not (as in our development client),
 * looping until all sites are connected, and printing the number of connections generated.
 * Package your program as a static method count() that takes N as argument and returns the number
 * of connections and a main() that takes N from the command line, calls count(), and prints the returned value.
 *
 ****************************************************************************************************/
public class RandomConnections {

    public static void main(String[] args) {
        int n = 100_000;
        System.out.println(count(n));
    }

    public static int count(int n) {
        UFQuickUnion uf = new UFQuickUnion(n);
        while (uf.count() != 1)
            unionRandomSites(n, uf);

        return uf.getNumberOfPairs();
    }

    private static void unionRandomSites(int n, UFQuickUnion uf) {
        for (int i = 0; i < n; i++) {
            int p = StdRandom.uniform(n);
            int q = StdRandom.uniform(n);
            uf.union(p, q);
        }
    }

}
