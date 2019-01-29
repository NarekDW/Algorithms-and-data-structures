package _1_Fundamentals._1_4_Analysis_of_Algorithms;

import common.StdRandom;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ThreeSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int opposite = -(a[i] + a[j]);
                int index = BinarySearch.rank(a, opposite);
                if (index > j) count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] x = IntStream.generate(() -> StdRandom.uniform(-1_000_000, 1_000_000))
                .limit(10_000)
                .distinct()
                .toArray();

        System.out.println("x.length = " + x.length);
        Stopwatch stopwatch = new Stopwatch();
        int count = count(x);
        double v = stopwatch.elapsedTime();
        System.out.println("count = " + count + " : time = " + v);

//        int countSlow = ThreeSum.count(x);
//        System.out.println("countSlow = " + countSlow);
    }
}
