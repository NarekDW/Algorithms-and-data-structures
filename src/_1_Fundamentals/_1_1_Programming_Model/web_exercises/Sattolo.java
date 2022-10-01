package _1_Fundamentals._1_1_Programming_Model.web_exercises;

import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 1. Sattolo's algorithm.
 * Write a program Sattolo.java that generates a uniformly distributed cycle of length N using Sattolo's algorithm.
 *
 ****************************************************************************************************/

public class Sattolo {

    public static int[] shuffle(int[] a) {
        int n = a.length;
        for (int i = n - 1; i > 1; i--) {
            int r = StdRandom.uniform(i);
            int tmp = a[i];
            a[i] = a[r];
            a[r] = tmp;
        }
        return a;
    }

}
