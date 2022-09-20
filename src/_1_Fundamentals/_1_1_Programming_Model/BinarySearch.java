package _1_Fundamentals._1_1_Programming_Model;

import common.In;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > key) hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int rank2(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);

        for (int i = 0; i < whitelist.length - 1; i++) {
            if (whitelist[i] == whitelist[i + 1]) {
                for (int j = i+1; j < whitelist.length - 2; j++) {
                    whitelist[j] = whitelist[j + 1];
                }
            }
        }

//        while (!StdIn.isEmpty()) {
//            int key = StdIn.readInt();
//            if (rank2(key, whitelist) < 0) {
//                System.out.println(key);
//            }
//        }

        for (int i = 0; i < 3_000_000; i++) {
//            if (rank2(i, whitelist) < 0) {
            if (rank(i, whitelist) < 0) {
                System.out.println(i);
            }
        }
    }
}
