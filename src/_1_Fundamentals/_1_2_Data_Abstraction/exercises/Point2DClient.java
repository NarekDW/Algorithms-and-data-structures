package _1_Fundamentals._1_2_Data_Abstraction.exercises;

import _1_Fundamentals._1_2_Data_Abstraction.Point2D;
import common.StdDraw;
import common.StdRandom;

import java.util.ArrayList;

/*****************************************************************************************************
 *
 * 1.2.1 Write a Point2D client that takes an integer value N from the command line,
 * generates N random points in the unit square, and computes the distance separating
 * the closest pair of points.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class Point2DClient {
    public static void main(String[] args) {

        int n = 10;

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);

        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        double min = 100;
        Point2D point1 = null;
        Point2D point2 = null;
        ArrayList<Double> dists = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++) {
                double distance = points[i].distanceTo(points[j]);
                dists.add(distance);
                if (distance < min) {
                    point1 = points[i];
                    point2 = points[j];
                    min = distance;
                }
            }

        System.out.println("min = " + min);
        dists.sort(Double::compareTo);
        dists.forEach(System.out::println);
        point1.drawTo(point2);
    }
}
