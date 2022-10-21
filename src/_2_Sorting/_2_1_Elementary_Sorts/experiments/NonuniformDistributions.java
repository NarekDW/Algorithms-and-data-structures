package _2_Sorting._2_1_Elementary_Sorts.experiments;

import common.Stopwatch;
import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.StdRandom;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class NonuniformDistributions {

    public static void main(String[] args) {
//        Double[] gaussian = init(StdRandom::gaussian, 10_000);
//        Integer[] poisson = initInt(() -> StdRandom.poisson(1.0), 10_000);
//        Double[] uniform = init(StdRandom::uniform, 10_000);
//        Integer[] discrete = initInt(() -> StdRandom.discrete(new int[]{1, 2, 3, 4, 5}), 10_000);
//
//        System.out.println("poisson = " + time(poisson));
//        System.out.println("uniform = " + time(uniform));
//        System.out.println("gaussian = " + time(gaussian));
//        System.out.println("discrete = " + time(discrete));


        Double[] gaussian = init(StdRandom::gaussian, 100);
        Integer[] poisson = initInt(() -> StdRandom.poisson(1.0), 100);
        Double[] uniform = init(StdRandom::uniform, 100);
        Integer[] discrete = initInt(() -> StdRandom.discrete(new int[]{1, 1, 1, 1, 1}), 100);

//        printArray(gaussian, "gaussian");
//        printArray(poisson, "poisson");
//        printArray(uniform, "uniform");
//        printArray(discrete, "discrete");

        System.out.println("discrete");
        Arrays.stream(discrete)
                .collect(groupingBy(Function.identity(), counting()))
                .forEach((key, count) -> System.out.println(key + "  " + count));

        System.out.println("gaussian");
        Arrays.stream(gaussian)
                .collect(groupingBy(Function.identity(), counting()))
                .forEach((key, count) -> System.out.println(key + "  " + count));

        System.out.println("poisson");
        Arrays.stream(poisson)
                .collect(groupingBy(Function.identity(), counting()))
                .forEach((key, count) -> System.out.println(key + "  " + count));

        System.out.println("uniform");
        Arrays.stream(uniform)
                .collect(groupingBy(Function.identity(), counting()))
                .forEach((key, count) -> System.out.println(key + "  " + count));

    }

    private static <T> void printArray(T[] t, String name) {
        System.out.println(name);
        for (T x : t)
            System.out.print(x + " ");
        System.out.println();
    }

    public static <T extends Comparable> double time(T[] t) {
        Stopwatch stopwatch = new Stopwatch();
        Insertion.sort(t);
        return stopwatch.elapsedTime();
    }

    public static Integer[] initInt(Supplier<Integer> generator, int n) {
        Integer[] r = new Integer[n];
        for (int i = 0; i < n; i++)
            r[i] = generator.get();
        return r;
    }

    public static Double[] init(Supplier<Double> generator, int n) {
        Double[] r = new Double[n];
        for (int i = 0; i < n; i++)
            r[i] = generator.get();
        return r;
    }

}
