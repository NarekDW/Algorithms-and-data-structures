package _2_Sorting._2_1_Elementary_Sorts.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative.Deque;

/*****************************************************************************************************
 *
 * 2.1.14 Dequeue sort. Explain how you would sort a deck of cards, with the restric-
 * tion that the only allowed operations are to look at the values of the top two cards, to
 * exchange the top two cards, and to move the top card to the bottom of the deck.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class DequeueSort {

    public static void sortDequeu(Deque<Integer> deque) {
        int n = deque.size();
        for (int i = 0; i < n; i++) {
            int max = deque.popLeft();
            for (int j = 0; j < n - 1; j++) {
                Integer item = deque.popLeft();
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
        Deque<Integer> deque = new Deque<>();

        deque.pushRight(5);
        deque.pushRight(1);
        deque.pushRight(3);
        deque.pushRight(2);
        deque.pushRight(4);
        deque.pushRight(9);
        deque.pushRight(7);
        deque.pushRight(8);
        deque.pushRight(6);

        sortDequeu(deque);

        for (Integer i : deque) {
            System.out.println(i + " ");
        }
    }

}
