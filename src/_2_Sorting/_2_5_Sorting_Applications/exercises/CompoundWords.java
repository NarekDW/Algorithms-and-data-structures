package _2_Sorting._2_5_Sorting_Applications.exercises;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.BinarySearch;
import _2_Sorting._2_3_Quicksort.Quick;

/*****************************************************************************************************
 * <p>
 * 2.5.2 Write a program that reads a list of words from standard input and prints all
 * two-word compound words in the list. For example, if after , thought , and afterthought
 * are in the list, then afterthought is a compound word.
 * <p>
 * Complexity of this Algorithm is ~ N^2 * log(N)
 *
 ****************************************************************************************************/
public class CompoundWords {

    public static void compounds(String[] words) {
        Quick.sort(words); // ~ N log(N)
        int n = words.length;
        // ~ 2 * N^2 * log(N)
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                binarySearch(words, words[i] + words[j]); // ~log(N)
                binarySearch(words, words[j] + words[i]); // ~log(N)
            }
    }

    private static void binarySearch(String[] words, String key) {
        int rank = BinarySearch.rank(key, words);
        if (rank != -1)
            System.out.println(words[rank]);
    }

    public static void main(String[] args) {
        String[] w = {"w", "s", "ws", "sw", "a", "b", "c", "ab", "d", "dc", "x", "z", "xa"};
        compounds(w);
    }

}
