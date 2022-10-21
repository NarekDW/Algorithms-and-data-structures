package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.Stopwatch;
import common.StdRandom;

import java.util.Arrays;
import java.util.stream.DoubleStream;

import static java.lang.Math.abs;

/*****************************************************************************************************
 *
 * 1.4.16 Closest pair (in one dimension). Write a program that, given an array a[] of N
 * double values, finds a closest pair : two values whose difference is no greater than the
 * the difference of any other pair (in absolute value). The running time of your program
 * should be linearithmic in the worst case.
 *
 * 1.4.17 Farthest pair (in one dimension). Write a program that, given an array a[] of N
 * double values, finds a farthest pair : two values whose difference is no smaller than the
 * the difference of any other pair (in absolute value). The running time of your program
 * should be linear in the worst case.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class ClosestAndFarthestElems {

    // ~ N^2
    public static void printClosests(double[] a) {
        int i = 0;
        int j = 1;
        double delta = Double.MAX_VALUE;
        for (int x = 0; x < a.length; x++)
            for (int y = x + 1; y < a.length; y++) {
                double abs = abs(a[x] - a[y]);
                if (abs < delta) {
                    delta = abs;
                    i = x;
                    j = y;
                }
            }

        System.out.printf("a[i] = %f, a[j] = %f, delta = %f\n", a[i], a[j], delta);
    }

    // ~ N^2
    public static void printFarthest(double[] a) {
        int i = 0;
        int j = 1;
        double delta = Double.MIN_VALUE;
        for (int x = 0; x < a.length; x++)
            for (int y = x + 1; y < a.length; y++) {
                double abs = abs(a[x] - a[y]);
                if (abs > delta) {
                    delta = abs;
                    i = x;
                    j = y;
                }
            }

        System.out.printf("a[i] = %f, a[j] = %f, delta = %f\n", a[i], a[j], delta);
    }

    public static void printClosestsFast(double[] a) {
        Arrays.sort(a);
        double delta = Double.MAX_VALUE;
        int x = 0;
        for (int i = 0; i < a.length - 1; i++) {
            double abs = abs(a[i] - a[i + 1]);
            if (abs < delta) {
                delta = abs;
                x = i;
            }
        }

        System.out.printf("a[i] = %f, a[j] = %f, delta = %f\n", a[x], a[x + 1], delta);
    }

    public static void printFarthestFast(double[] a) {
        Arrays.sort(a);
        double delta = Double.MIN_VALUE;
        int x = 0;
        for (int i = 0; i < a.length - 1; i++) {
            double abs = abs(a[i] - a[i + 1]);
            if (abs > delta) {
                delta = abs;
                x = i;
            }
        }

        System.out.printf("a[i] = %f, a[j] = %f, delta = %f\n", a[x], a[x + 1], delta);
    }


    public static void main(String[] args) {
        double[] x = DoubleStream.generate(() -> StdRandom.uniform(-1000.0, 1000.0))
                .limit(200_000)
                .distinct()
                .toArray();

        System.out.println("x.length = " + x.length);

        Stopwatch stopwatch = new Stopwatch();
        // N^2 time ~ 24.858 sec
        printFarthest(x);
        double v = stopwatch.elapsedTime();
        System.out.println("N^2 time ~ " + v);

        Stopwatch stopwatch1 = new Stopwatch();
        // N*log(N) time ~ 0.055 sec
        printFarthestFast(x);
        double v1 = stopwatch1.elapsedTime();
        System.out.println("N*log(N) time ~ " + v1);
    }

}
