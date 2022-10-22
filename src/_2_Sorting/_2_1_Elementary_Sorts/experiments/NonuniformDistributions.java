package _2_Sorting._2_1_Elementary_Sorts.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Shell;
import common.StdRandom;
import common.Stopwatch;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/*****************************************************************************************************
 * <p>
 * 2.1.35 Nonuniform distributions.
 * Write a client that generates test data by randomly ordering objects using other distributions
 * than uniform, including the following:
 *  - Gaussian
 *  - Poisson
 *  - Geometric
 *  - Discrete (see Exercise 2.1.28 for a special case)
 * Develop and test hypotheses about the effect of such input on the performance of the algorithms
 * in this section.
 *
 ****************************************************************************************************/
public class NonuniformDistributions {

    public static void main(String[] args) {
        int size = 1_000;
        Double[] gaussian = generateDoubleArray(StdRandom::gaussian, size);
        Integer[] poisson = generateIntegerArray(() -> StdRandom.poisson(1.0), size);
        Double[] uniform = generateDoubleArray(StdRandom::uniform, size);
        Integer[] discrete = generateIntegerArray(() -> StdRandom.discrete(new int[]{1, 1, 1, 1, 1}), size);

        printArray(gaussian, "gaussian");
        printArray(poisson, "poisson");
        printArray(uniform, "uniform");
        printArray(discrete, "discrete");

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

        System.out.println("Shell sort discrete: " + time(discrete, Shell::sort));
        System.out.println("Shell sort gaussian: " + time(gaussian, Shell::sort));
        System.out.println("Shell sort poisson: " + time(poisson, Shell::sort));
        System.out.println("Shell sort uniform: " + time(uniform, Shell::sort));
    }

    private static <T> void printArray(T[] t, String name) {
        System.out.println(name);
        for (T x : t)
            System.out.print(x + " ");
        System.out.println();
    }

    public static <T extends Comparable<?>> double time(T[] t, Consumer<Comparable<?>[]> sortAlgo) {
        Stopwatch stopwatch = new Stopwatch();
        sortAlgo.accept(t);
        return stopwatch.elapsedTime();
    }

    public static Integer[] generateIntegerArray(Supplier<Integer> generator, int n) {
        Integer[] r = new Integer[n];
        for (int i = 0; i < n; i++)
            r[i] = generator.get();
        return r;
    }

    public static Double[] generateDoubleArray(Supplier<Double> generator, int size) {
        Double[] arr = new Double[size];
        for (int i = 0; i < size; i++)
            arr[i] = generator.get();
        return arr;
    }

}
