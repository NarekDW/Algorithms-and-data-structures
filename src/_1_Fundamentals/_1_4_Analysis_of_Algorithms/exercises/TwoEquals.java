package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

import common.StdRandom;

import java.util.Arrays;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class TwoEquals {

    public static int count(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (a[i] == a[j]){
                    cnt++;
                    break;
                }


        return cnt;
    }

    public static int countFast(int[] a) {
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1])
                cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] x = IntStream.generate(() -> StdRandom.uniform(-1000, 1000))
                .limit(10_000_000)
                .toArray();

        System.out.println("count = " + count(x));
        System.out.println("countFast = " + countFast(x));
    }

}
