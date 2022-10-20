package _2_Sorting._2_1_Elementary_Sorts.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative.Deque;
import common.SortUtils;
import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 2.1.14 Dequeue sort. Explain how you would sort a deck of cards, with the restriction
 * that the only allowed operations are to look at the values of the top two cards, to
 * exchange the top two cards, and to move the top card to the bottom of the deck.
 *
 ****************************************************************************************************/
public class DequeueSort {

    public static void sortDeque(Deque<Double> deque) {
        int n = deque.size();
        for (int i = 0; i < n; i++) {
            Double max = deque.popLeft();
            for (int j = 0; j < (n - 1); j++) {
                Double item = deque.popLeft();
                if (item > max) {
                    deque.pushRight(max);
                    max = item;
                } else {
                    deque.pushRight(item);
                }
            }
            deque.pushRight(max);
        }
    }


    public static void main(String[] args) {
        int size = 10_000;
        Deque<Double> deque = generateQueue(size);
        sortDeque(deque);
        assertDequeSorted(size, deque);
    }

    private static Deque<Double> generateQueue(int size) {
        Deque<Double> deque = new Deque<>();
        for (int i = 0; i < size; i++)
            deque.pushLeft(StdRandom.uniform());
        return deque;
    }

    private static void assertDequeSorted(int size, Deque<? extends Comparable<?>> deque) {
        Comparable<?> prev = deque.popLeft();
        for (int i = 0; i < size - 1; i++) {
            Comparable<?> next = deque.popLeft();
            if (!SortUtils.less(prev, next))
                throw new RuntimeException();
            prev = next;
        }

        if (!deque.isEmpty())
            throw new RuntimeException();
    }
}
