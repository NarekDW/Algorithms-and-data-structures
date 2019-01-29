package _1_Fundamentals._1_4_Analysis_of_Algorithms;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.creative.TwoSumSorted;
import common.StdRandom;

import java.util.Arrays;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class TwoSumFast {

    public static int countSlow(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == 0) cnt++;
            }
        }

        return cnt;
    }

    public static int count(int[] a) {
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            int opposite = -a[i];
            int index = BinarySearch.rank(a, opposite);
            if (index > i) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] x = IntStream.generate(() -> StdRandom.uniform(-1_000_000, 1_000_000))
                .limit(10_000_000)
                .distinct()
                .toArray();

        System.out.println("x.length = " + x.length);

        Stopwatch stopwatch = new Stopwatch();
        // ~ Nlog(N)
        int count = count(x);
        double v = stopwatch.elapsedTime();
        System.out.println("count = " + count + " : time = " + v);

        // Faster
        Stopwatch stopwatch2 = new Stopwatch();
        // ~ N
        int cntSorted = TwoSumSorted.count(x);
        double v2 = stopwatch2.elapsedTime();
        System.out.println("cntSorted = " + cntSorted + " : time = " + v2);



//        int countSlow = countSlow(x);
//        System.out.println("countSlow = " + countSlow);
    }
}

