package _1_Fundamentals._1_1_Programming_Model.experiments;

import _1_Fundamentals._1_1_Programming_Model.BinarySearch;
import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import common.StdRandom;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/*****************************************************************************************************
 * <p>
 * 1.1.38 Binary search versus brute-force search.
 * Write a program BruteForceSearch that uses the brute-force search method given on page 48 and
 * compare its running time on your computer with that of BinarySearch for largeW.txt and largeT.txt.
 *
 ****************************************************************************************************/
public class BinarySearchVersusBruteForceSearch {

    public static void main(String[] args) {
        int n = 100_000_000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = i;

        double timeBinarySearch = execute(n, array, (o, o2) -> BinarySearch.rank((Integer) o, (int[]) o2));
        System.out.println("BinarySearch time: " + timeBinarySearch + " sec.");
        double timeBruteSearch = execute(n, array, (o, o2) -> rank((Integer) o, (int[]) o2));
        System.out.println("BruteSearch time: " + timeBruteSearch + " sec.");

        System.out.println("The difference: " + timeBruteSearch / timeBinarySearch);
    }

    static double execute(int n, int[] array, BiFunction func) {
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < n / 100_000; i++) {
            int randomInt = StdRandom.uniform(n * 3);
            int rank = (int) func.apply(randomInt, array);
        }
        return stopwatch.elapsedTime();
    }

    public static int rank(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }

}
