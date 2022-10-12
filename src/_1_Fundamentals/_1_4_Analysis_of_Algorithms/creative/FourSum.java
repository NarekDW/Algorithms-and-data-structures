package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.In;

import java.util.Arrays;

/*****************************************************************************************************
 * <p>
 * 1.4.14 4-sum. Develop an algorithm for the 4-sum problem.
 *
 ****************************************************************************************************/
public class FourSum {

    public static int count(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                for (int k = j + 1; k < a.length; k++)
                    for (int l = k + 1; l < a.length; l++)
                        if (a[i] + a[j] + a[k] + a[l] == 0)
                            cnt++;
        return cnt;
    }


    public static void main(String[] args) {
        In in = null;
        try {
            in = new In("resources/ints/32Kints.txt");
            int[] a = Arrays.stream(in.readAllInts()).limit(200).toArray();
            System.out.println("Start FourSum::count");
            int count = count(a);

            if (count != 34)
                throw new RuntimeException(String.valueOf(count));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (in != null)
                in.close();
        }
    }

}
