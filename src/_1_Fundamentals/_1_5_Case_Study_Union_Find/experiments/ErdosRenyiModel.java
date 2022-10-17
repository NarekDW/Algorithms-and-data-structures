package _1_Fundamentals._1_5_Case_Study_Union_Find.experiments;

import _1_Fundamentals._1_5_Case_Study_Union_Find.creative.RandomConnections;

/*****************************************************************************************************
 * <p>
 * 1.5.21 Erdös-Renyi model. Use your client from Exercise 1.5.17
 * to test the hypothesis that the number of pairs generated to get one component is ~ 1⁄2N ln N.
 *
 ****************************************************************************************************/
public class ErdosRenyiModel {

    public static void main(String[] args) {
        for (int i = 1_000; i < 1_000_000; i *= 2) {
            int count = RandomConnections.count(i);
            // Approximately the same
            System.out.println("Number of generated values: " + count);
            System.out.println("1/2 n * ln(n) = " + (i / 2.0 * Math.log(i)) + "\n");
        }
    }

}
