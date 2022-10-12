package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.StopwatchCPU;

/*****************************************************************************************************
 * <p>
 * 1.4.25 Throwing two eggs from a building. Consider the previous question, but now
 * suppose you only have two eggs, and your cost model is the number of throws. Devise
 * a strategy to determine F such that the number of throws is at most 2√N, then find a
 * way to reduce the cost to ~c √F. This is analogous to a situation where search hits (egg
 * intact) are much cheaper than misses (egg broken).
 *
 ****************************************************************************************************/
public class ThrowingTwoEggs {

    private final int f; // egg is broken if it is thrown off f or higher
    private final int floors;
//    private int step;

    public ThrowingTwoEggs(int floors, int f) {
        this.floors = floors;
        this.f = f;
//        this.step = firstStepIndex(floors.length);
    }

    /**
     * 1 approach is to devide floors on equal sections of size sqrt(floor),
     * number of throws ~ 2* sqrt(N)
     */
    public int findFloorSlow() {
        int sectionSize = (int) Math.ceil(Math.sqrt(floors));
        int i = sectionSize;
        for (; i < floors + 1; i += sectionSize)
            if (isBroken(i))
                return findFloorInSection(i - sectionSize + 1, i);

        return findFloorInSection(i - sectionSize + 1, floors);
    }


    /**
     * 2 approach is to try to use fixed amount of eggs drops.
     * Lets say we want to use ~ n tries in the worst case
     * then first drop will be from n'th floor, (n throws)
     * second - 1 + (n - 1) throws
     * third -  2 + (n - 2) throws
     * ...
     * n - 1 -  n + (n - 1) throws
     * <p>
     * floors: n, n + n - 1, n + (n - 1) + (n - 2) ...
     * <p>
     * then we get Arithmetic progression, with sum: (a1 + an) * n/2
     * a1 - first element, an - last element, n - number of elements
     * <p>
     * we get equation: (n + 1) * n / 2 >= numberOfFloors
     * in order to find n, we need to resolve equation n^2 + n - 2*numberOfFloors = 0
     */
    public int findFloorFast() {
        int n = calculateN();
        int i = n;
        for (; i < floors + 1; n--, i += n)
            if (isBroken(i))
                return findFloorInSection(i - n + 1, i);

        return findFloorInSection(i - n + 1, floors);
    }

    private int findFloorInSection(int from, int to) {
        for (int i = from; i < to; i++)
            if (isBroken(i))
                return i;
        return to;
    }

    private boolean isBroken(int index) {
        return index >= f;
    }

    private int calculateN() {
        // n^2 + n - 2 * floors = 0
        // D = b^2 - 4*a*c
        // n = (-b + sqrt(D)) / (2 * a)
        int discriminant = 1 + 8 * floors;
        double n = (-1 + Math.sqrt(discriminant)) / 2.0;
        return (int) Math.ceil(n);
    }


    public static void main(String[] args) {
        testAlgo();
        testSlowAlgoPerfomance();
        testFastAlgoPerfomance();
    }

    private static void testAlgo() {
        int floors = 1000;
        for (int i = 1; i < floors; i++) {
            ThrowingTwoEggs eggs = new ThrowingTwoEggs(floors, i);
            int floorSlow = eggs.findFloorSlow();
            int floorFast = eggs.findFloorFast();
            if (floorSlow != i)
                throw new RuntimeException();
            if (floorSlow != floorFast)
                throw new RuntimeException();
        }
    }

    private static void testSlowAlgoPerfomance() {
        StopwatchCPU stopwatch = new StopwatchCPU();
        int floors = 1_000_000;
        for (int i = 1; i < floors; i++) {
            ThrowingTwoEggs eggs = new ThrowingTwoEggs(floors, i);
            int floorSlow = eggs.findFloorSlow();
            if (floorSlow != i)
                throw new RuntimeException();
        }
        System.out.println("Time: " + stopwatch.elapsedTime() + " sec.");
    }

    private static void testFastAlgoPerfomance() {
        StopwatchCPU stopwatch = new StopwatchCPU();
        int floors = 100_000;
        for (int i = 1; i < floors; i++) {
            ThrowingTwoEggs eggs = new ThrowingTwoEggs(floors, i);
            int floorFast = eggs.findFloorFast();
            if (floorFast != i)
                throw new RuntimeException(String.valueOf(i));
        }
        System.out.println("Time: " + stopwatch.elapsedTime() + " sec.");
    }

}
