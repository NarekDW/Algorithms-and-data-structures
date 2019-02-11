package _2_Sorting._2_5_Sorting_Applications.exercises;

import _2_Sorting._2_3_Quicksort.Quick;

/*****************************************************************************************************
 *
 * 2.5.8 Write a program Frequency that reads strings from standard input and prints
 * the number of times each string occurs, in descending order of frequency.
 *
 * Complexity is ~ 2 * N * log(N) + N ~~ N * log(N)
 * And it uses ~ N extra space
 *
 ****************************************************************************************************/
public class Frequency {

    public void frequency(String[] words) {
        int n = words.length;
        // Sort initial array ~ N * log(N)
        Quick.sort(words);
        WordFreq[] arr = new WordFreq[n];
        int j = 0;
        // Select first elem
        arr[j++] = new WordFreq(words[0], 1);
        // Copy each word to the new array and increment its frequency each time it appears ~ N
        for (int i = 1; i < n; i++)
            if (words[i].compareTo(words[i - 1]) == 0)
                arr[j - 1].increment();
            else
                arr[j++] = new WordFreq(words[i], 1);

        // Sort result array by frequencies ~ N * log(N)
        Quick.sort(arr, 0, j - 1);
        // Print
        for (int i = 0; i < j; i++)
            System.out.println(arr[i]);
    }

    class WordFreq implements Comparable<WordFreq> {
        String word;
        int frequency;

        public WordFreq(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        void increment() {
            frequency++;
        }

        @Override
        public String toString() {
            return word + " : " + frequency;
        }

        // Reversed
        @Override
        public int compareTo(WordFreq that) {
            if (this.frequency > that.frequency) return -1;
            else if (this.frequency < that.frequency) return 1;
            else return 0;
        }
    }


    // Test
    public static void main(String[] args) {
        String[] x = {"a", "b", "c", "a", "a", "c", "b", "c", "b", "a"};
        Frequency f = new Frequency();
        f.frequency(x);
    }
}
