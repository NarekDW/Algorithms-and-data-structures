package _2_Sorting._2_1_Elementary_Sorts.exercises;

/*****************************************************************************************************
 *
 * 1. Sorting networks.
 * Write a program Sort3.java with three if statements (and no loops)  that reads in
 * three integers a, b, and c from the command line and prints them out in ascending order.
 *
 ****************************************************************************************************/
public class Sort3 {

    public static void main(String[] args) {
        sort(1, 2, 3);
        sort(3, 2, 1);
        sort(2, 1, 3);
        sort(2, 3, 1);
        sort(1, 3, 2);
    }

    public static void sort(int a, int b, int c) {
        if (a > b) {int t = a; a = b; b = t;}
        if (a > c) {int t = c; c = a; a = t;}
        if (c < b) {int t = c; c = b; b = t;}
        System.out.println(a + " " + b + " " + c);
    }


}
