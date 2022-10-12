package _1_Fundamentals._1_4_Analysis_of_Algorithms.experiments;

import common.StdRandom;

import java.util.ArrayList;


public class BirthdayProblem {

    public static void main(String[] args) {
        int n = 100_000;
        System.out.println((int) Math.sqrt(Math.PI * n / 2));
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int value = StdRandom.uniform(n);
            if (list.contains(value)) {
                System.out.println(list.size());
                return;
            }

            list.add(value);
        }
    }

}
