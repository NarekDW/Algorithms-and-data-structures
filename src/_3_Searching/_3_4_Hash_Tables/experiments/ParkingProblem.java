package _3_Searching._3_4_Hash_Tables.experiments;

import common.StdRandom;

/*****************************************************************************************************
 * 3.4.43 Parking problem. (D. Knuth) Run experimental studies to validate the hypoth-
 * esis that the number of compares needed to insert M random keys into a linear-probing
 * table of size M is ~cM 3/2 , where c =  /2.
 ****************************************************************************************************/
public class ParkingProblem {

    public static void main(String[] args) {
        int m = 100;
        LinearProbingHashST<Double, Double> st = new LinearProbingHashST<>(m);
        for (int i = 0; i < m; i++) {
            double x = StdRandom.uniform();
            st.put(x, x);
        }

        System.out.println("compares = " + st.compares);
        double theoretical = Math.sqrt((Math.PI / 2)) * Math.pow(m, 1.5);
        System.out.println("Theoretical = " + theoretical);
    }

}
