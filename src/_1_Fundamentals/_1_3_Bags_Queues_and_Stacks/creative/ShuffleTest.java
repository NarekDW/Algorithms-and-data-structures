package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

import _1_Fundamentals._1_1_Programming_Model.web_exercises.Sattolo;
import common.StdRandom;

import java.util.Arrays;
import java.util.function.Function;

public class ShuffleTest {

    public static void main(String[] args) {
        System.out.println("Test default shuffle from StdRandom:");
        testShuffle(ShuffleTest::shuffle, 5); // ~ 60 - 70 % of distinct arrays

        System.out.println("Test shuffle2:");
        testShuffle(ShuffleTest::shuffle2, 5); // ~ 60 - 70 % of distinct arrays

        System.out.println("Test shuffle2:");
        testShuffle(Sattolo::shuffleFunc, 5); // ~ 20 % of distinct arrays
    }

    public static void testShuffle(Function<int[], int[]> function, int arrayLength) {
        int theoreticalNumberOfDistinctArrays = factorial(arrayLength);
        int[][] arrays = new int[theoreticalNumberOfDistinctArrays][arrayLength];
        int[] initial = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++)
            initial[i] = i;
        for (int i = 0; i < theoreticalNumberOfDistinctArrays; i++)
            arrays[i] = function.apply(initial);
        int numberOfDistinctArrays = numberOfDistinctArrays(arrays);
        System.out.print("arrays: " + theoreticalNumberOfDistinctArrays + " - distinct: " + numberOfDistinctArrays + " - ");
        System.out.println("% of distinct: " + numberOfDistinctArrays * 1.0 / theoreticalNumberOfDistinctArrays + "\n");
    }

    public static int[] shuffle(int[] a) {
        int n = a.length;
        int[] result = new int[n];
        System.arraycopy(a, 0, result, 0, n);
        for (int i = 0; i < n; i++) {
            int r = i + StdRandom.uniform(n - i);
            int temp = result[i];
            result[i] = result[r];
            result[r] = temp;
        }

        return result;
    }

    public static int[] shuffle2(int[] a) {
        int n = a.length;
        int[] result = new int[n];
        System.arraycopy(a, 0, result, 0, n);
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(n);     // between i and n-1
            int temp = result[i];
            result[i] = result[r];
            result[r] = temp;
        }

        return result;
    }

    private static int factorial(int n) {
        int x = 1;
        for (int i = 2; i <= n; i++)
            x *= i;
        return x;
    }

    private static int numberOfDistinctArrays(int[][] x) {
        int count = x.length;
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                if (Arrays.equals(x[i], x[j])) {
                    count--;
                    break;
                }
            }
        }

        return count;
    }
}
