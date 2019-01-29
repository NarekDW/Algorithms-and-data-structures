package _1_Fundamentals._1_1_Programming_Model.creative;


import common.StdDraw;
import common.StdRandom;

import static java.lang.Math.PI;

/*****************************************************************************************************
 *
 * 1.1.31 Random connections. Write a program that takes as command-line arguments
 * an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size
 * .05 on the circumference of a circle, and then, with probability p for each pair of points,
 * draws a gray line connecting them.
 *
 ****************************************************************************************************/
public class RandomConnections {

    public static void main(String[] args) {
        drawLines(100, 0.01);
        drawLines(100, 0.99);
    }

    private static void drawLines(int n, double p) {
        int radius = 100;
        StdDraw.setXscale(-150, 150);
        StdDraw.setYscale(-150, 150);
        double diff = (2 * PI) / n;
        double[][] points = new double[n][2];
        int index = 0;
        for (double i = 0; index < n; i = i + diff, index++) {
            points[index][0] = Math.sin(i) * radius;
            points[index][1] = Math.cos(i) * radius;
            StdDraw.point(points[index][0], points[index][1]);
        }

        for (int i = 0; i < points.length - 1; i++) {
            if (StdRandom.bernoulli(p))
                StdDraw.line(points[i][0], points[i][1], points[i + 1][0], points[i + 1][1]);

            if (i == points.length - 2 && StdRandom.bernoulli(p))
                StdDraw.line(points[i + 1][0], points[i + 1][1], points[0][0], points[0][1]);

        }

        StdDraw.save(
                "/home/narek/IdeaProjects/algorithms-sedgewick/Fundamentals/src/main/resources/" +
                        p + ".png"
        );

        StdDraw.clear();
    }
}
