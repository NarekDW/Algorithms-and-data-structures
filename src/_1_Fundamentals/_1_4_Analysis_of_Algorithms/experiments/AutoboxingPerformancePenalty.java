package _1_Fundamentals._1_4_Analysis_of_Algorithms.experiments;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.Stopwatch;
import common.StdOut;

public class AutoboxingPerformancePenalty {

    public static void main(String[] args) {
        int cap = 200_000_005;
        int items = 10_000_000;

        // items = 100_000_000 ~ 38.354 sec
        // items = 10_000_000 ~ 3.321 sec
        FixedCapacityStack<Integer> integers = new FixedCapacityStack<>(cap);
        Stopwatch t1 = new Stopwatch();
        for (int i = 0; i < items; i++)
            integers.push(i);
        StdOut.println("integer elapsed time = " + t1.elapsedTime());

        // items = 100_000_000 ~ 0.119 sec
        // items = 10_000_000 ~ 0.018 sec
        FixedCapacityStackOfInts ints = new FixedCapacityStackOfInts(cap);
        Stopwatch t2 = new Stopwatch();
        for (int i = 0; i < items; i++)
            ints.push(i);
        StdOut.println("int elapsed time = " + t2.elapsedTime());
    }

}

class FixedCapacityStack<Item> {

    private Item[] a;
    private int n;

    FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
        n = 0;
    }

    void push(Item item) {
        a[n++] = item;
    }
}

class FixedCapacityStackOfInts {

    private int[] a;
    private int n;

    FixedCapacityStackOfInts(int cap) {
        a = new int[cap];
        n = 0;
    }

    void push(int item) {
        a[n++] = item;
    }
}
