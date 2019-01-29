package _1_Fundamentals._1_4_Analysis_of_Algorithms.experiments;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _1_Fundamentals._1_4_Analysis_of_Algorithms.ThreeSum;
import common.In;
import common.StdOut;

public class Naive3SumImplementation {

    public static void main(String[] args) {
        In in = new In("4Kints.txt");
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        // ~ 16.223 sec
        int count = ThreeSum.count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime() + " : count - " + count);

        Stopwatch timer2 = new Stopwatch();
        // ~ 95.831 sec
        int countSlow = ThreeSum.countSlow(a);
        StdOut.println("elapsed time = " + timer2.elapsedTime() + " : countSlow - " + countSlow);
    }

}
