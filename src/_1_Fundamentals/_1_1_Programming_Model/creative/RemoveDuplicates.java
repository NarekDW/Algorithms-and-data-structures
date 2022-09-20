package _1_Fundamentals._1_1_Programming_Model.creative;

import common.StdRandom;

import java.util.Arrays;

/*****************************************************************************************************
 *
 * 1.1.28 Remove duplicates.
 * Modify the test client in BinarySearch to remove any duplicate keys in the whitelist after the sort.
 *
 ****************************************************************************************************/
public class RemoveDuplicates {

    // 1.1.28
    static int[] removeDuplicates(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int[] temp = new int[n];
        int j = 0;
        for (int i = 0; i < n - 1; i++)
            if (a[i] != a[i + 1])
                temp[j++] = a[i];
        temp[j] = a[n - 1];

        int[] result = new int[j];
        for (int i = 0; i < j; i++)
            result[i] = temp[i];

        return result;
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        for (int i = 0; i < 100; i++) {
            int[] a = new int[n];
            for (int j = 0; j < n; j++)
                a[j] = StdRandom.uniform(10);
            int[] result = removeDuplicates(a);
            Arrays.sort(result);
            for (int j = 0; j < result.length - 1; j++) {
                if (result[j] == result[j + 1])
                    throw new RuntimeException();
            }
        }

    }
}
