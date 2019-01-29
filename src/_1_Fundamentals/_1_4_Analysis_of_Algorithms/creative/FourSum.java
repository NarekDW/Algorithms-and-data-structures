package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.StdRandom;

import java.util.stream.IntStream;

/*****************************************************************************************************
 *
 * 1.4.14 4-sum. Develop an algorithm for the 4-sum problem.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class FourSum {

    public static void printElems(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    for (int l = k + 1; l < a.length; l++) {
                        if (a[i] + a[j] + a[k] + a[l] == 0)
                            System.out.println(a[i] + " " + a[j] + " " + a[k] + " " + a[l]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] x = IntStream.generate(() -> StdRandom.uniform(-1_000_000, 1_000_000))
                .limit(100)
                .distinct()
                .toArray();

        printElems(x);
    }

}
