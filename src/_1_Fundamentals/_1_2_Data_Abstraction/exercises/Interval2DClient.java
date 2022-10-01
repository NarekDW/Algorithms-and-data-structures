package _1_Fundamentals._1_2_Data_Abstraction.exercises;

import _1_Fundamentals._1_2_Data_Abstraction.Interval1D;
import _1_Fundamentals._1_2_Data_Abstraction.Interval2D;
import _1_Fundamentals._1_2_Data_Abstraction.Point2D;
import common.StdDraw;
import common.StdIn;
import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 1.2.3 Write an Interval2D client that takes commandline arguments N, min, and max
 * and generates N random 2D intervals whose width and height are uniformly distributed
 * between min and max in the unit square.
 * Draw them on StdDraw and print the number of pairs of intervals that intersect
 * and the number of intervals that are contained in one another.
 *
 ****************************************************************************************************/
public class Interval2DClient {

    static class Pair {
        private final double point1;
        private final double point2;

        public Pair(double min, double max) {
            this.point1 = StdRandom.uniform(min, max);
            this.point2 = StdRandom.uniform(min, max);
        }

        public double min() {
            return Math.min(point1, point2);
        }

        public double max() {
            return Math.max(point1, point2);
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Pair pair = new Pair(StdIn.readDouble(), StdIn.readDouble());
        double min = pair.min();
        double max = pair.max();

        Interval2D[] intervals = generateIntervals(n, min, max);
        drawCanvasAndIntervals(intervals);
        showNumberOfIntersections(intervals);
    }

    private static void showNumberOfIntersections(Interval2D[] intervals) {
        int intersections = 0;
        int containedOneAnother = 0;
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intervals[i].intersects(intervals[j]))
                    intersections++;
                if (contains(intervals[i], intervals[j]) || contains(intervals[j], intervals[i]))
                    containedOneAnother++;
            }
        }

        System.out.println("Intersections: " + intersections);
        System.out.println("Contained one another: " + containedOneAnother);
    }

    private static void drawCanvasAndIntervals(Interval2D[] intervals) {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenColor(StdDraw.BLUE);
        for (Interval2D interval : intervals)
            interval.draw();
    }

    private static Interval2D[] generateIntervals(int n, double min, double max) {
        Interval2D[] intervals = new Interval2D[n];
        for (int i = 0; i < n; i++)
            intervals[i] = generateInterval2D(min, max);
        return intervals;
    }

    public static boolean contains(Interval2D first, Interval2D second) {
        double x0 = second.getX().min();
        double x1 = second.getX().max();

        double y0 = second.getY().min();
        double y1 = second.getY().max();
        return first.contains(new Point2D(x1, y1)) && first.contains(new Point2D(x0, y0));
    }

    public static Interval2D generateInterval2D(double min, double max) {
        Pair pair1 = new Pair(min, max);
        Pair pair2 = new Pair(min, max);
        return new Interval2D(
                new Interval1D(pair1.min(), pair1.max()),
                new Interval1D(pair2.min(), pair2.max()));
    }

}
