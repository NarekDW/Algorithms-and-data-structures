package _1_Fundamentals._1_1_Programming_Model.experiments;

import _1_Fundamentals._1_1_Programming_Model.web_exercises.Sattolo;
import common.StdRandom;

import static common.StdRandom.uniform;

public class BadShuffling {

    public static void main(String[] args) {
        EmpiricalShuffleCheck.shuffleTest(10, 100_000, o -> shuffle((int[]) o));
        showOrderingPossibilities(11);
    }

    // Show that the resulting order is not equally likely to be one of the N! possibilities.
    // Note: From my observations it is approximately the same...
    static void showOrderingPossibilities(int n) {
        int numberOfVariations = factorial(n);
        int[][] store = new int[numberOfVariations][n];
        for (int i = 0; i < numberOfVariations; i++) {
            int[] array = generateArray(n);
//            shuffle(array);
//            StdRandom.shuffle(array);
            Sattolo.shuffle(array);
            store[i] = array;
        }
        int[] randomArray = store[StdRandom.uniform(store.length)];
        int numberOfAppearance = getNumberOfAppearance(store, randomArray);
        System.out.println("Number of appearance: " + numberOfAppearance);
    }

    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = uniform(n);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    static int getNumberOfAppearance(int[][] store, int[] randomElement) {
        int count = 0;
        for (int i = 1; i < store.length; i++) {
            int[] array = store[i];
            if (areArraysEqual(randomElement, array))
                count++;
        }
        return count;
    }

    static boolean areArraysEqual(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    private static int factorial(int n) {
        int x = 1;
        for (int i = 2; i <= n; i++)
            x *= i;
        return x;
    }

    static int[] generateArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = i;
        return array;
    }
}
