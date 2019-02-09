package _2_Sorting._2_4_Priority_Queues.creative;

import _2_Sorting._2_4_Priority_Queues.MinPQ;
import common.StdOut;

/*****************************************************************************************************
 *
 * 2.4.25 Computational number theory. Write a program CubeSum.java that prints out
 * all integers of the form a^3 + b^3 where a and b are integers between 0 and N in sorted
 * order, without using excessive space. That is, instead of computing an array of the N^2
 * sums and sorting them, build a minimum-oriented priority queue, initially containing
 * (0^3 , 0, 0), (1^3 , 1, 0), (2^3 , 2, 0), . . . , (N^3 , N, 0). Then, while the priority queue is nonempty,
 * remove the smallest item(i^3 + j^3 , i, j), print it, and then, if j < N, insert the item (i^3 + (j+1)^3 ,
 * i, j+1). Use this program to find all distinct integers a, b, c, and d between 0 and 10^6
 * such that a^3 + b^3 = c^3 + d^3 .
 *
 ****************************************************************************************************/
public class CubeSum implements Comparable<CubeSum> {

    private final long sum;
    private final long i;
    private final long j;

    public CubeSum(long i, long j) {
        this.sum = i * i * i + j * j * j;
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(CubeSum o) {
        if (o.sum > this.sum) return -1;
        if (o.sum < this.sum) return 1;
        return 0;
    }

    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }


    public static void main(String[] args) {

        int n = 1_000_000;

        // initialize priority queue
        MinPQ<CubeSum> pq = new MinPQ<>();
        for (long i = 0; i <= n; i++)
            pq.insert(new CubeSum(i, 0));

        // find smallest sum, print it out, and update
        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            StdOut.println(s);
            if (s.j < n)
                pq.insert(new CubeSum(s.i, s.j + 1));
        }
    }
}
