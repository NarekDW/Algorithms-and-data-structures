package _3_Searching.experiments;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _3_Searching.BinarySearchST;
import _3_Searching.creative.ArrayST;
import common.StdRandom;

/*****************************************************************************************************
 *
 * 3.1.33 Driver for self-organizing search. Write a driver program for self-organizing
 * search implementations (see Exercise 3.1.22) that uses get() to fill a symbol table
 * with N keys, then does 10 N successful searches according to a predefined probability
 * distribution. Use this driver to compare the running time of your implementation from
 * Exercise 3.1.22 with BinarySearchST for N = 10 3 , 10 4 , 10 5 , and 10 6 using the prob-
 * ability distribution where search hits the i th smallest key with probability 1/2 i .
 *
 *
 * Notes:
 *  Pareto distribution gives almost equal times (on Small N)
 *
 ****************************************************************************************************/
public class DriverForSelfOrganizingSearch {

    public static void main(String[] args) {
//        int n = 1_000;
        int n = 100_000;
        System.out.println(n);

        Stopwatch sw1 = new Stopwatch();
        BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<>(n);
        for (int i = 0; i < n; i++)
            binarySearchST.put(i, i);

        for (int i = 0; i < 10 * n; i++)
            binarySearchST.get(StdRandom.poisson(1));
//            binarySearchST.get((int) (StdRandom.pareto(3.0) - 1));
//            binarySearchST.get((int) (StdRandom.gaussian() * n));
//            binarySearchST.get(StdRandom.uniform(n));
        double t1 = sw1.elapsedTime();
        System.out.println("BinarySearchST time: " + t1);

        Stopwatch sw2 = new Stopwatch();
        ArrayST<Integer, Integer> arrayST = new ArrayST<>();
        for (int i = 0; i < n; i++)
            arrayST.put(i, i);

        for (int i = 0; i < 10 * n; i++)
            arrayST.get(StdRandom.poisson(1));
//            arrayST.get((int) (StdRandom.pareto(3.0) - 1));
//            arrayST.get((int) (StdRandom.gaussian() * n));
//            arrayST.get(StdRandom.uniform(n));
        double t2 = sw2.elapsedTime();
        System.out.println("ArrayST time: " + t2);

    }

}
