package _2_Sorting._2_2_Mergesort.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises.DoubleLinked;
import common.StdRandom;

/*****************************************************************************************************
 *
 * 2.2.18 Shuffling a linked list. Develop and implement a divide-and-conquer algo-
 * rithm that randomly shuffles a linked list in linearithmic time and logarithmic extra
 * space.
 *
 ****************************************************************************************************/

@SuppressWarnings("ALL")
public class ShufflingLinkedList {

    public static <T extends Comparable> void shuffle(DoubleLinked<T> linked) {
        int n = linked.size();
        int logN = (int) (Math.log(n) / Math.log(2));
        DoubleLinked<T>.DoubleNode[] aux = new DoubleLinked.DoubleNode[logN + 1];
        DoubleLinked<T>.DoubleNode tmp = linked.first;

        int step = n / logN;
        aux[0] = tmp;
        for (int i = 1; i < logN + 1; i++) {
            for (int j = 0; j < step; j++)
                tmp = tmp.next;
            aux[i] = tmp;
        }

        for (int i = 0; i < n; i++) {
            int p1 = getUniform(logN + 1);
            int p2;
            do {
                p2 = getUniform(logN + 1);
            } while (p2 == p1);

            int offset1 = getUniform(step);
            int offset2 = getUniform(step);

            shuffle(aux, p1, p2, offset1, offset2);
        }

        // move reference to the firs item
        while (tmp.prev != null)
            tmp = tmp.prev;
        linked.first = tmp;
    }

    private static <T extends Comparable> void shuffle(DoubleLinked<T>.DoubleNode[] aux,
                                                       int point1,
                                                       int point2,
                                                       int offset1,
                                                       int offset2) {
        DoubleLinked<T>.DoubleNode a1 = aux[point1];
        DoubleLinked<T>.DoubleNode a2 = aux[point2];

        // access to the first item to be changed
        int off1 = offset1, off2 = offset2;
        while (a1.next != null && off1 > 0) {
            a1 = a1.next;
            off1--;
        }

        // access to the second item to be changed
        while (a2.next != null && off2 > 0) {
            a2 = a2.next;
            off2--;
        }

        if (offset1 == 0 || a1.next == null)
            aux[point1] = a2;
        if (offset2 == 0 || a2.next == null)
            aux[point2] = a1;

        DoubleLinked<T>.DoubleNode a2Next = a2.next;
        DoubleLinked<T>.DoubleNode a2Prev = a2.prev;
        DoubleLinked<T>.DoubleNode a1Next = a1.next;
        DoubleLinked<T>.DoubleNode a1Prev = a1.prev;

        if (a1 == a2Prev || a2 == a1Next) {
            a1.next = a2Next;
            if (a1.next != null)
                a1.next.prev = a1;
            a1.prev = a2;
            a2.next = a1;
            a2.prev = a1Prev;
            if (a2.prev != null)
                a2.prev.next = a2;
        } else if (a2 == a1Prev || a1 == a2Next) {
            a2.next = a1Next;
            if (a2.next != null)
                a2.next.prev = a2;
            a2.prev = a1;
            a1.next = a2;
            a1.prev = a2Prev;
            if (a1.prev != null)
                a1.prev.next = a1;
        } else {
            if (a1Prev != null)
                a1Prev.next = a2;
            a2.prev = a1Prev;

            if (a1Next != null)
                a1Next.prev = a2;
            a2.next = a1Next;

            if (a2Prev != null)
                a2Prev.next = a1;
            a1.prev = a2Prev;

            if (a2Next != null)
                a2Next.prev = a1;
            a1.next = a2Next;
        }
    }

    private static int getUniform(int points) {
        return StdRandom.uniform(points);
    }


    public static void main(String[] args) {
        for (int j = 0; j < 1000; j++) {
            DoubleLinked<Integer> x = new DoubleLinked<>();

            for (int i = 0; i < 100; i++)
                x.addLast(i + 1);

            shuffle(x);
            show(x);
        }
    }

    private static <T> void show(DoubleLinked<T> linked) {
        System.out.println("size: " + linked.size());
        DoubleLinked<T>.DoubleNode x = linked.first;
        while (x != null) {
            System.out.print(x.item + " ");
            x = x.next;
        }
    }
}
