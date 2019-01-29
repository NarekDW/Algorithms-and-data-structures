package _1_Fundamentals._1_4_Analysis_of_Algorithms.experiments;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;
import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.FixedCapacityStack;
import common.StdOut;

public class ResizingArraysVersusLinkedLists {

    public static void main(String[] args) {
        int cap = 10_000_000;

        Stopwatch stopwatch = new Stopwatch();
        // 3.63 sec
        FixedCapacityStack<Integer> array = new FixedCapacityStack<>(cap);
        for (int i = 0; i < cap; i++)
            array.push(i);
        StdOut.println("elapsed time = " + stopwatch.elapsedTime());

        Stopwatch stopwatch2 = new Stopwatch();
        // 8.941 sec
        Stack<Integer> linked = new Stack<>();
        for (int i = 0; i < cap; i++)
            linked.push(i);
        StdOut.println("elapsed time = " + stopwatch2.elapsedTime());

    }

}
