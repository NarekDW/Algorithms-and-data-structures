package _3_Searching._3_2_Binary_Search_Trees.experiments;

import _3_Searching._3_2_Binary_Search_Trees.BST;
import common.StdRandom;

/*****************************************************************************************************
 *
 * 3.2.40 Height. Run empirical studies to estimate average BST height by running 100
 * trials of the experiment of inserting N random keys into an initially empty tree, for N =
 * 10 4 , 10 5 , and 10 6 . Compare your results against the 2.99 lg N estimate that is described
 * in the text.
 *
 ****************************************************************************************************/
public class AvgHeight {

    public static void main(String[] args) {
        int n = 10_000;
        int h = 0;
        int times = 100;
        for (int i = 0; i < times; i++) {
            BST<Double, Double> bst = new BST<>();
            for (int j = 0; j < n; j++) {
                double rand = StdRandom.uniform();
                bst.put(rand, rand);
            }
             h += bst.height();
        }

        System.out.println("In Practice Average height: " + h / times * 1.0);
        System.out.println("Theoretical Average height: " + 2.99 * Math.log(n));
    }

}
