package _1_Fundamentals._1_1_Programming_Model.creative;

import java.util.Arrays;

/*****************************************************************************************************
 *
 * 1.1.28 Remove duplicates. Modify the test client in BinarySearch to remove any du-
 * plicate keys in the whitelist after the sort.
 *
 * 1.1.29 Equal keys. Add to BinarySearch a static method rank() that takes a key and
 * a sorted array of int values (some of which may be equal) as arguments and returns the
 * number of elements that are smaller than the key and a similar method count() that
 * returns the number of elements equal to the key. Note : If i and j are the values returned
 * by rank(key, a) and count(key, a) respectively, then a[i..i+j-1 ] are the values in
 * the array that are equal to key .
 *
 ****************************************************************************************************/
public class MyBinarySearch {

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 5, 5, 6, 7, 8, 8, 9, 10, 11, 13, 15, 15};
        int[] b = removeDuplicates(a);
        final int rankLess = rankLess(6, a);
        int res = rank(13, b);

        System.out.println("rankLess = " + rankLess);
        System.out.println(res);
    }

    public static int rankLess(int key, int[] a) {
        int previous = a[0];
        int res = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < key && a[i] != previous || i == 0 && a[i] < key) res++;
            previous = a[i];
        }

        return res;
    }


    static int[] removeDuplicates(int[] arr) {
        final int n = arr.length;
        int[] temp = new int[n];
        int j = 0;
        for (int i = 0; i < n - 1; i++)
            if (arr[i] != arr[i + 1])
                temp[j++] = arr[i];
        temp[j] = arr[n - 1];
        return Arrays.copyOfRange(temp, 0, j);
    }

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 1);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth) {
        System.out.printf("%" + depth + "d : %d\n", lo, hi);
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, depth + 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, depth + 1);
        else return mid;
    }

}
