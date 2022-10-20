package _2_Sorting._2_1_Elementary_Sorts.creative;

import common.StdDraw;

public class Animation {

    public static void drawInitial(Integer[] a, int n, int initialIndex) {
        int max = a[0];
        for (Integer i : a)
            if (i > max)
                max = i;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, max);
        draw(a, initialIndex);
    }

    public static void draw(Integer[] a, int index) {
        StdDraw.clear();
        for (int i = 0; i < a.length; i++) {
            if (i == index)
                StdDraw.setPenColor(StdDraw.RED);
            else
                StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(i, 0, 0.4, a[i] / 2.0);
        }
    }

}
