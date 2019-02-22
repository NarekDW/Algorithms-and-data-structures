package _3_Searching._3_1_Elementary_Symbol_Tables.experiments;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _3_Searching._3_1_Elementary_Symbol_Tables.SequentialSearchST;
import common.StdIn;
import common.StdOut;

import java.io.FileInputStream;
import java.io.IOException;

public class PerformanceValidation {

    public static void main(String[] args) throws IOException {
        int distinct = 0, words = 0;
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        String path = "/home/narek/Desktop/algorithms/leipzig1M.txt";
        System.setIn(new FileInputStream(path));

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
