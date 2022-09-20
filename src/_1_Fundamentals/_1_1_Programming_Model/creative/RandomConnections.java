package _1_Fundamentals._1_1_Programming_Model.creative;


import common.StdDraw;
import common.StdRandom;

import static java.lang.Math.PI;

/*****************************************************************************************************
 * <p>
 * 1.1.31 Random connections. Write a program that takes as command-line arguments
 * an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size
 * .05 on the circumference of a circle, and then, with probability p for each pair of points,
 * draws a gray line connecting them.
 *
 ****************************************************************************************************/
public class RandomConnections {

    public static void main(String[] args) {
        drawLines(100, 0.01);
        drawLines(99, 0.99);
    }

    private static void drawLines(int n, double p) {
        int radius = 100;
        StdDraw.setXscale(-150, 150);
        StdDraw.setYscale(-150, 150);
        double diff = (2 * PI) / n;
        double[][] points = new double[n][2];
        int index = 0;
        for (double i = 0; index < n; i = i + diff, index++) {
            points[index][0] = Math.cos(i) * radius;
            points[index][1] = Math.sin(i) * radius;
            StdDraw.point(points[index][0], points[index][1]);
        }

        // Choose one of them
        connectAllPoints(n, p, points);
//        connectNeighborhoodPoints(p, points);

        StdDraw.save("./src/resources/fundamentals/" + p + ".png");
        StdDraw.clear();
    }

    private static void connectNeighborhoodPoints(double p, double[][] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (StdRandom.bernoulli(p))
                StdDraw.line(points[i][0], points[i][1], points[i + 1][0], points[i + 1][1]);

            if (i == points.length - 2 && StdRandom.bernoulli(p))
                StdDraw.line(points[i + 1][0], points[i + 1][1], points[0][0], points[0][1]);
        }
    }

    private static void connectAllPoints(int n, double p, double[][] points) {
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (StdRandom.bernoulli(p))
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
    }
}
