package _2_Sorting._2_1_Elementary_Sorts.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import _2_Sorting._2_1_Elementary_Sorts.Shell;
import common.SortUtils;
import common.StdDraw;
import common.StopwatchCPU;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/*****************************************************************************************************
 * <p>
 * 2.1.32 Plot running times.
 * Write a client that uses StdDraw to plot the average running times of the algorithm for
 * random inputs and various values of the array size.
 * You may add one or two more command-line arguments. Strive to design a useful tool.
 *
 ****************************************************************************************************/
public class Plot {

    public static void plotRunningTime(Consumer<Comparable<?>[]> algorithm,
                                       Stream<Integer> arraysSizes,
                                       int numberOfTrials,
                                       Color color,
                                       boolean drawAxis) {
        Map<Double, Double> result = new HashMap<>();
        List<Double> sizes = arraysSizes.map(Double::valueOf).toList();
        for (Double size : sizes) {
            double time = 0.0;
            for (int i = 0; i < numberOfTrials; i++) {
                Double[] doubles = SortUtils.generateArrayDouble(size.intValue(), false);
                StopwatchCPU stopwatchCPU = new StopwatchCPU();
                algorithm.accept(doubles);
                time += stopwatchCPU.elapsedTime();
            }
            result.put(size, time / numberOfTrials);
        }

        result.forEach((arraySize, averageTime) -> System.out.println(arraySize + " : " + averageTime));

        if (drawAxis) {
            Double maxSize = sizes.get(sizes.size() - 1);
            Double maxTime = result.get(maxSize);
            drawAxis(maxSize, maxTime);
        }

        drawPoints(result, color);
    }

    private static void drawAxis(double maxX, double maxY) {
        StdDraw.setPenColor(Color.BLACK);
        double minXaxis = -1 * maxX / 4;
        double minYaxis = -1 * maxY / 4;
        StdDraw.setXscale(minXaxis, maxX + maxX / 10);
        StdDraw.setYscale(minYaxis, maxY + maxY / 10);

        StdDraw.line(minXaxis / 2, 0, maxX, 0);
        StdDraw.line(0, minYaxis / 2, 0, maxY);

        StdDraw.text(minXaxis / 2, minYaxis / 2, "   0");
        StdDraw.text(minXaxis / 2, maxY, String.valueOf(maxY).substring(0, 6));
        StdDraw.text(maxX, minYaxis / 2, String.valueOf((int) maxX));
    }

    private static void drawPoints(Map<Double, Double> map, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.01);
        map.forEach(StdDraw::point);
    }


    public static void main(String[] args) {
        int numberOfTrials = 2;
        int limit = 7;
        Stream<Integer> sizes = Stream.iterate(1_000, i -> i * 2)
                .limit(limit);

        Stream<Integer> sizes2 = Stream.iterate(1_000, i -> i * 2)
                .limit(limit);

        plotRunningTime(Insertion::sort, sizes, numberOfTrials, Color.RED, true);
        plotRunningTime(Shell::sort, sizes2, numberOfTrials, Color.GREEN, false);
    }
}
