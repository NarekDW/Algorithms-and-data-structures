package _3_Searching;

/******************************************************************************
 *  Compilation:  javac FrequencyCounter.java
 *  Execution:    java FrequencyCounter L < input.txt
 *  Dependencies: ST.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/31elementary/tnyTale.txt
 *                https://algs4.cs.princeton.edu/31elementary/tale.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 *                https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 *
 *  Read in a list of words from standard input and print out
 *  the most frequently occurring word that has length greater than
 *  a given threshold.
 *
 *  % java FrequencyCounter 1 < tinyTale.txt
 *  it 10
 *
 *  % java FrequencyCounter 8 < tale.txt
 *  business 122
 *
 *  % java FrequencyCounter 10 < leipzig1M.txt
 *  government 24763
 *
 * /home/narek/Desktop/algorithms/leipzig1M.txt
 *
 ******************************************************************************/


import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _3_Searching._3_2_Binary_Search_Trees.BST;
import _3_Searching._3_2_Binary_Search_Trees.experiments.ArrayRepresentationBST;
import _3_Searching._3_3_Balanced_Search_Trees.RedBlackBSTOrigin;
import _3_Searching._3_4_Hash_Tables.LinearProbingHashST;
import _3_Searching._3_4_Hash_Tables.SeparateChainingHashST;
import common.StdIn;
import common.StdOut;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * The {@code FrequencyCounter} class provides a client for
 * reading in a sequence of words and printing a word (exceeding
 * a given length) that occurs most frequently. It is useful as
 * a test client for various symbol table implementations.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class FrequencyCounter {

    // Do not instantiate.
    private FrequencyCounter() {
    }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {

        Stopwatch stopwatch = new Stopwatch();

        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
//        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
//        BST<String, Integer> st = new BST<>();
//        RedBlackBSTOrigin<String, Integer> st = new RedBlackBSTOrigin<>();
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
//        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>(3610829);
        /*
        BST
        --------------------------------------------------------------------------------------------------------
        Gutenberg-tm 5  3
        distinct = 4266
        words    = 6695
        time: 0.345

        government 24763
        distinct = 165555
        words    = 1610829
        time: 12.024

        ArrayRepresentationBST:
        --------------------------------------------------------------------------------------------------------
        Gutenberg-tm 53
        distinct = 4266
        words    = 6695
        time: 0.356

        government 24763
        distinct = 165555
        words    = 1610829
        time: 12.503
         */
//        ArrayRepresentationBST<String, Integer> st = new ArrayRepresentationBST<>(1610829);

        if (args.length > 1)
            System.setIn(new FileInputStream(args[1]));

        Stopwatch put = new Stopwatch();
        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
//                st.put(key, st.getLastValue() + 1);
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }
        System.out.println("PUT: " + put.elapsedTime());

        Stopwatch get = new Stopwatch();
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        System.out.println("GET: " + get.elapsedTime());

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        double t = stopwatch.elapsedTime();
        System.out.println("time: " + t);

        System.out.println("Pirson: " + st.pirson());
    }

}

