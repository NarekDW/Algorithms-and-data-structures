package _1_Fundamentals._1_1_Programming_Model.experiments;

import common.StdRandom;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        int duplicates = duplicates(7);
        System.out.println("duplicates = " + duplicates);
    }

    static int duplicates(int n) {
        int x = (int) Math.pow(10, n);
        int[] a = new int[x];
        int[] b = new int[x];

        System.out.println(x);

        System.out.println("Start filling:");
        for (int i = 0; i < x; i++) {
            a[i] = (int) (StdRandom.uniform() * Math.pow(10, 6));
            b[i] = (int) (StdRandom.uniform() * Math.pow(10, 6));
        }

        System.out.println("Start sorting:");
        Arrays.sort(b);

        System.out.println("Start searching:");
        int res = 0;
        for (int i : a)
//            if (contains(i, b)) res++;
            if (containsBin(i, b)) res++;

        return res;
    }

    static boolean contains(int element, int[] source) {
        for (int i : source)
            if (i == element)
                return true;

        return false;
    }

    static boolean containsBin(int element, int[] source) {
        return containsBin(element, source, 0, source.length - 1);
    }

    static boolean containsBin(int key, int[] a, int lo, int hi) {
        if (lo > hi) return false;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return containsBin(key, a, lo, mid - 1);
        else if (key > a[mid]) return containsBin(key, a, mid + 1, hi);
        else return true;
    }
}
