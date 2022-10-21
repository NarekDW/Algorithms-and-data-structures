package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.StopwatchCPU;

import java.util.function.Function;

/*****************************************************************************************************
 * <p>
 * 1.4.24 Throwing eggs from a building. Suppose that you have an N-story building and
 * plenty of eggs. Suppose also that an egg is broken if it is thrown off floor F or higher,
 * and unhurt otherwise. First, devise a strategy to determine the value of F such that the
 * number of broken eggs is ~lg N when using ~lg N throws, then find a way to reduce the
 * cost to ~2lg F.
 *
 ****************************************************************************************************/
public class ThrowingEggs {

    private final int floor;

    public ThrowingEggs(int floor) {
        this.floor = floor;
    }

    public boolean isBroken(int x) {
        return x >= floor;
    }


    public int findFloor(int[] x) {
        return findFloor(x, 0, x.length - 1);
    }

    // ~ log(N)
    private int findFloor(int[] x, int lo, int hi) {
        if (lo > x.length - 1) return x[x.length - 1];
        if (lo > hi) return x[lo];
        int mid = lo + (hi - lo) / 2;
        if (isBroken(x[mid])) return findFloor(x, lo, mid - 1);
        else return findFloor(x, mid + 1, hi);
    }


    public int findFloorFast(int[] x) {
        return findFloorFast(x, 1);
    }

    // ~ 2*log(floor)
    private int findFloorFast(int[] x, int i) {
        int mid = i * 2;
        if (mid > x.length - 1) return x[x.length - 1];
        if (isBroken(x[mid]))
            return findFloor(x, i - 1, i * 2);
        else
            return findFloorFast(x, i * 2);
    }

    private int findFloorFaster(int[] x, int first, int second) {
        int index = Math.min(first, x.length - 1);
        if (isBroken(x[index]))
            return findFloor(x, Math.min(second - first, 0), index);
        else
            return findFloorFaster(x, second, first + second);
    }

    public int findFloorFaster(int[] x) {
        return findFloorFaster(x, 0, 1);
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < 11; i++) {
            ThrowingEggs eggs = new ThrowingEggs(i);
            int floor = eggs.findFloor(x);
            int floorFast = eggs.findFloorFast(x);
            int floorFaster = eggs.findFloorFaster(x);

            if (i == 0)
                if (floor != 1 || floorFast != 1 || floorFaster != 1)
                    throw new RuntimeException();

            if (i != 0 && floor != i && floorFast != i && floorFaster != i)
                throw new RuntimeException();
        }

        int n = 1_000_000_000;
        int[] floors = new int[n];
        for (int i = 0; i < n; i++)
            floors[i] = i;

        ThrowingEggs eggs = new ThrowingEggs(65);
        testAlgo(eggs::findFloor, floors);
        testAlgo(eggs::findFloorFast, floors);
        testAlgo(eggs::findFloorFaster, floors);
    }

    private static void testAlgo(Function<int[], Integer> findFloorFunction, int[] floors) {
        StopwatchCPU stopwatch = new StopwatchCPU();
        int floor = -1;
        for (int i = 0; i < 100; i++)
            floor = findFloorFunction.apply(floors);
        System.out.println("Floor: " + floor + ", time: " + stopwatch.elapsedTime() + " sec.");
    }
}
