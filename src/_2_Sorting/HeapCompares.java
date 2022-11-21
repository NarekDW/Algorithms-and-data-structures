package _2_Sorting;

import _2_Sorting._2_4_Priority_Queues.MaxPQ;
import _2_Sorting._2_4_Priority_Queues.creative.LinkedListPQ;
import common.SortUtils;
import common.StopwatchCPU;

public class HeapCompares {

    public static void main(String[] args) {
        maxPQvsLinkedListPQ();
    }

    private static void maxPQvsLinkedListPQ() {
        int size = 10_000_000;
        MaxPQ<Integer> arrayPQ = new MaxPQ<>(size);
        Integer[] integers = SortUtils.generateArrayInteger(size);

        StopwatchCPU stopwatch = new StopwatchCPU();
        for (Integer i : integers)
            arrayPQ.insert(i);

        Integer prev = arrayPQ.delMax();
        while (!arrayPQ.isEmpty()) {
            Integer curr = arrayPQ.delMax();
            if (curr > prev)
                throw new RuntimeException();
            prev = curr;
        }

        System.out.println("MaxPQ: " + stopwatch.elapsedTime()); // 16

        LinkedListPQ<Integer> linkedListPQ = new LinkedListPQ<>();
        StopwatchCPU stopwatch2 = new StopwatchCPU();
        for (Integer i : integers)
            linkedListPQ.insert(i);

        Integer prev2 = linkedListPQ.delMax();
        while (!linkedListPQ.isEmpty()) {
            Integer curr2 = linkedListPQ.delMax();
            if (curr2 > prev2)
                throw new RuntimeException();
            prev2 = curr2;
        }

        System.out.println("LinkedListPQ: " + stopwatch2.elapsedTime()); // 40
    }

}
