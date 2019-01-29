package _1_Fundamentals._1_1_Programming_Model.creative;

import common.StdDraw;
import common.StdIn;

/*****************************************************************************************************
 *
 * 1.1.32 Histogram. Suppose that the standard input stream is a sequence of double
 * values. Write a program that takes an integer N and two double values l and r from the
 * command line and uses StdDraw to plot a histogram of the count of the numbers in the
 * standard input stream that fall in each of the N intervals defined by dividing (l , r) into
 * N equal-sized intervals.
 *
 ****************************************************************************************************/
public class Histogram {

    public static void main(String[] args) {
        int n = StdIn.readInt();
        double l = StdIn.readDouble();
        double r = StdIn.readDouble();
        double[][] points = getPoints(l, r, n);
        double halfWidth = 0.710 * (r - l) / (n * 2);

        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, 10);

        while (StdIn.hasNextLine()) {
            double num = StdIn.readDouble();
            for (double[] point : points) {
                double x1 = point[0];
                double x2 = point[1];
                if (x1 <= num && num < x2) {
                    point[2] += 1;
                    double xmid = x1 + (x2 - x1) / 2.0;
                    double count = point[2];
                    StdDraw.filledRectangle(xmid, count / 2, halfWidth, count / 2);
                }
            }
        }
    }

    private static double[][] getPoints(double l, double r, int n) {
        double[][] points = new double[n][3];
        double diff = (r - l) / n;
        int index = 0;
        for (double i = l; i < r && index < n; i = i + diff, index++) {
            points[index][0] = i;
            points[index][1] = i + diff;
        }

        return points;
    }

}
