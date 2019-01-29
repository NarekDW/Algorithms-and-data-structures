package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.BinarySearch;

public class BinarySearchTest {
    public static void main(String[] args) {

        int[] x = {1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 6};

        System.out.println("lowestIndex 3: " + BinarySearch.lowestIndex(x, 3));
        System.out.println("lowestIndex 2: " + BinarySearch.lowestIndex(x, 2));
        System.out.println("lowestIndex 1: " + BinarySearch.lowestIndex(x, 1));
    }
}
