package _1_Fundamentals._1_1_Programming_Model.experiments;

import _1_Fundamentals._1_1_Programming_Model.exercises.Exercises;
import common.StdRandom;

import java.util.function.Consumer;

/*****************************************************************************************************
 * <p>
 * 1.1.36 Empirical shuffle check.
 * Run computational experiments to check that our shuffling code on page 32 works as advertised.
 * Write a program ShuffleTest that takes command-line arguments M and N, does N shuffles of an array of size M
 * that is initialized with a[i] = i before each shuffle, and prints an M-by-M table such
 * that row i gives the number of times i wound up in position j for all j.
 * All entries in the array should be close to N/M.
 *
 ****************************************************************************************************/
public class EmpiricalShuffleCheck {

    public static void main(String[] args) {
        shuffleTest(10, 100_000, o -> StdRandom.shuffle((int[]) o));
    }

    static void shuffleTest(int m, int n, Consumer shuffle) {
        int[][] store = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] array = new int[m];
            for (int j = 0; j < m; j++)
                array[j] = j;
            shuffle.accept(array);
            store[i] = array;
        }

        int[][] occurrence = new int[m][m];
        for (int i = 0; i < m; i++) {
            int[] rowOccurrence = new int[m];
            for (int j = 0; j < n; j++) {
                rowOccurrence[store[j][i]] += 1;
            }
            occurrence[i] = rowOccurrence;
        }

        int[][] resultOccurrences = Exercises.transposition(occurrence);
        System.out.println("Occurrences:");
        displayHeader(m, 7);
        for (int i = 0; i < m; i++) {
            System.out.printf("%7d", i);
            for (int j = 0; j < m; j++) {
                System.out.printf("%7d", resultOccurrences[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        double closeResult = n * 1.0 / m;
        double[][] distributionPrecisions = new double[m][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < m; j++)
                distributionPrecisions[i][j] = resultOccurrences[i][j] / closeResult;

        System.out.println("Precisions:");
        displayHeader(m, 7);
        for (int i = 0; i < m; i++) {
            System.out.printf("%7d", i);
            for (int j = 0; j < m; j++) {
                System.out.printf("%7.3f", distributionPrecisions[i][j]);
            }
            System.out.println();
        }
    }

    private static void displayHeader(int m, int elementSpace) {
        System.out.printf("%" + elementSpace + "s", "");
        for (int i = 0; i < m; i++) {
            System.out.printf("%7d", i);
        }
        System.out.println();
    }

}
