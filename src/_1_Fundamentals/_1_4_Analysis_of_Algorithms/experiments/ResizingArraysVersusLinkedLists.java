package _1_Fundamentals._1_4_Analysis_of_Algorithms.experiments;

import common.Stopwatch;
import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;
import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.FixedCapacityStack;
import common.StdOut;

/*****************************************************************************************************
 * <p>
 *
 * 1.4.43 Resizing arrays versus linked lists.
 * Run experiments to validate the hypothesis that resizing arrays are faster than linked lists
 * for stacks (see Exercise 1.4.35 and Exercise 1.4.36). Do so by developing a version of DoublingRatio
 * that computes the ratio of the running times of the two programs.
 *
 ****************************************************************************************************/
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
