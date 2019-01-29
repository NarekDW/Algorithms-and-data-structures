package _2_Sorting._2_1_Elementary_Sorts.experiments;

import _2_Sorting.SortCompare;

public class DoublingTest {

    public static void main(String[] args) {
//        SortCompare.timeRandomInput("Insertion", 10_000, 1000);
        double prev = SortCompare.timeRandomInput("Selection", 1000, 1);
        for (int n = 2000; true; n += n) {
            double time = SortCompare.timeRandomInput("Selection", n, 1);
            System.out.printf("%6d %7.3f ", n, time);
            System.out.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }

}
