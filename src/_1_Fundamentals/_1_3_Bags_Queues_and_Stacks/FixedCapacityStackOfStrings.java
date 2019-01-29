package _1_Fundamentals._1_3_Bags_Queues_and_Stacks;

/******************************************************************************
 *  Compilation:  javac FixedCapacityStackOfStrings.java
 *  Execution:    java FixedCapacityStackOfStrings
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Stack of strings implementation with a fixed-size array.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java FixedCapacityStackOfStrings 5 < tobe.txt
 *  to be not that or be
 *
 *  Remark:  bare-bones implementation. Does not do repeated
 *  doubling or null out empty array entries to avoid loitering.
 *
 ******************************************************************************/

public class FixedCapacityStackOfStrings {

    private String[] a;
    private int n;

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
        n = 0;
    }

    void push(String item) {
        a[n++] = item;
    }

    String pop() {
        return a[--n];
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }
}
