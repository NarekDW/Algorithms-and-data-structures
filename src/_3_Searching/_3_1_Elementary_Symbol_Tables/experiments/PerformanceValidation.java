package _3_Searching._3_1_Elementary_Symbol_Tables.experiments;

import common.In;
import common.Stopwatch;
import _3_Searching._3_1_Elementary_Symbol_Tables.SequentialSearchST;
import common.StdIn;
import common.StdOut;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/*****************************************************************************************************
 * <p>
 * 3.1.35 Performance validation I. Run doubling tests that use the first N words of Tale of
 * Two Cities for various values of N to test the hypothesis that the running time of FrequencyCounter
 * is quadratic when it uses SequentialSearchST for its symbol table.
 *
 ****************************************************************************************************/
public class PerformanceValidation {

    public static void main(String[] args) throws IOException {
        int distinct = 0, words = 0;
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        String path = "https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt";
        System.setIn(new URL(path).openStream());

        double prevTime = 1.0;
        for (int i = 1_000; ; i = i * 2) {
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                String key = StdIn.readString();
                words++;
                if (st.contains(key)) {
                    st.put(key, st.getLastValue() + 1);
                } else {
                    st.put(key, 1);
                    distinct++;
                }
            }

            // find a key with the highest frequency count
            String max = "";
            st.put(max, 0);
            for (String word : st.keys()) {
                if (st.get(word) > st.get(max))
                    max = word;
            }
            double t = sw.elapsedTime();

            StdOut.println("N = " + i);
            StdOut.println(max + " " + st.get(max));
            StdOut.println("distinct = " + distinct);
            StdOut.println("words    = " + words);
            StdOut.println("time    = " + t);
            StdOut.println("prev/current    = " + t/prevTime);
            prevTime = t;
            System.out.println();
        }
    }

}
