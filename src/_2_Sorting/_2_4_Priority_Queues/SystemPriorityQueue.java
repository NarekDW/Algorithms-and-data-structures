package _2_Sorting._2_4_Priority_Queues;

import java.util.PriorityQueue;

public class SystemPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue pq = new PriorityQueue();
        pq.add(1);
        pq.add(10);
        pq.add(5);

        System.out.println(pq.poll()); // 1
        System.out.println(pq.poll()); // 5
        System.out.println(pq.poll()); // 10
    }

}
