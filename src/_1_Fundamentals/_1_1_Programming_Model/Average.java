package _1_Fundamentals._1_1_Programming_Model;


import common.StdIn;
import common.StdOut;

public class Average {

    public static void main(String[] args) {
        double sum = 0.0;
        int cnt = 0;

        while (!StdIn.isEmpty()) {
             sum += StdIn.readDouble();
             cnt++;
        }

        double avg = sum/cnt;
        StdOut.printf("The average - %.5f\n", avg);
    }

}
