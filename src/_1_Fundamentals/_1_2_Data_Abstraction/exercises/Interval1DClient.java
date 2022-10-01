package _1_Fundamentals._1_2_Data_Abstraction.exercises;

import _1_Fundamentals._1_2_Data_Abstraction.Interval1D;
import common.StdIn;

/*****************************************************************************************************
 * <p>
 * 1.2.2 Write an Interval1D client that takes an int value N as command-line argument,
 * reads N intervals (each defined by a pair of double values) from standard input,
 * and prints all pairs that intersect.
 *
 ****************************************************************************************************/
public class Interval1DClient {

    static class IntersectionPair {
        private final Interval1D interval1;
        private final Interval1D interval2;

        public IntersectionPair(Interval1D interval1, Interval1D interval2) {
            this.interval1 = interval1;
            this.interval2 = interval2;
        }

        @Override
        public String toString() {
            return "IntersectionPair: " +
                    "interval1={" + interval1.min() + " : " + interval1.max() + "}, " +
                    "interval2={" + interval2.min() + " : " + interval2.max() + "}  ";
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Interval1D[] intervals = new Interval1D[n];
        for (int i = 0; i < n; i++) {
            double x1 = StdIn.readDouble();
            double x2 = StdIn.readDouble();
            if (x1 < x2)
                intervals[i] = new Interval1D(x1, x2);
            else
                intervals[i] = new Interval1D(x2, x1);
        }

        IntersectionPair[] intersections = new IntersectionPair[amountOfCombinations(n, 2)];
        int intersectionIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intervals[i].intersects(intervals[j])) {
                    intersections[intersectionIndex++] = new IntersectionPair(intervals[i], intervals[j]);
                }
            }
        }

        for (IntersectionPair intersection : intersections)
            if (intersection != null)
                System.out.println(intersection);

    }

    public static int amountOfCombinations(int n, int k) {
        int x = 1;
        for (int i = n - k + 1; i <= n; i++)
            x *= i;

        int y = 1;
        for (int i = 1; i <= k; i++)
            y *= i;

        return x / y;
    }

}
