package _1_Fundamentals._1_1_Programming_Model.creative;

import java.util.Arrays;

public class RemoveElems {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 5, 6};
        int[] result = removeDuplicates(arr);

        for (int r : result) {
            System.out.print(r + " ");
        }
    }

    static int[] removeDuplicates(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        int j = 0;

        for (int i = 0; i < n - 1; i++)
            if (arr[i] != arr[i + 1])
                temp[j++] = arr[i];
        temp[j++] = arr[n - 1];

        return Arrays.copyOfRange(temp, 0, j);
    }

}
