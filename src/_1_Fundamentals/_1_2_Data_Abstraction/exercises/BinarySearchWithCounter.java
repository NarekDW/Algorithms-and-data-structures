package _1_Fundamentals._1_2_Data_Abstraction.exercises;

import _1_Fundamentals._1_2_Data_Abstraction.Counter;

/*****************************************************************************************************
 * <p>
 * 1.2.9 Instrument BinarySearch (page 47) to use a Counter to count the total number
 * of keys examined during all searches and then print the total after all searches are
 * complete. Hint : Create a Counter in main() and pass it as an argument to rank() .
 *
 ****************************************************************************************************/
public class BinarySearchWithCounter {

    public static void main(String[] args) {
        int[] b = new int[1000];
        for (int i = 0; i < 1000; i++) {
            b[i] = i;
        }

        System.out.println(rank(b, 1));
        System.out.println(rank(b, 10));
        System.out.println(rank(b, 100));
        System.out.println(rank(b, 500));
        System.out.println(rank(b, 899));
        System.out.println(rank(b, 999));
    }

    public static int rank(int[] src, int key) {
        Counter counter = new Counter("BinarySearch");
        int rank = rank(src, key, 0, src.length, counter);
        System.out.println(counter);
        return rank;
    }

    public static int rank(int[] src, int key, int lo, int hi, Counter c) {
        c.increment();
        if (lo > hi) return -1;
        int mid = lo + (hi - lo)/2;
        if (key < src[mid]) return rank(src, key, lo, mid - 1, c);
        else if (key > src[mid]) return rank(src, key, mid + 1, hi, c);
        else return mid;
    }

}
