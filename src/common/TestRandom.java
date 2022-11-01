package common;

import java.awt.*;

import static java.lang.Math.*;

public class TestRandom {

    public static void main(String[] args) {
        StdDraw.setYscale(-0.03, 0.1);
        StdDraw.setXscale(-15, 15);
        StdDraw.setPenColor(Color.RED);

        for (int i = 0; i < 1_000_000; i++) {
            double x = StdRandom.gaussian(0, 10);
            double y = fx(10, 0, x);
            StdDraw.point(x, y);
        }
    }


    private static double fx(double deviation, double mean, double x) {
        double part1 = 1 / sqrt(2 * PI * pow(deviation, 2));
        double exponent = -pow(x - mean, 2) / (2 * pow(deviation, 2));
        double part2 = pow(E, exponent);
        return part1 * part2;
    }
}
