package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

/*****************************************************************************************************
 *
 * 1.4.25 Throwing two eggs from a building. Consider the previous question, but now
 * suppose you only have two eggs, and your cost model is the number of throws. Devise
 * a strategy to determine F such that the number of throws is at most 2√N, then find a
 * way to reduce the cost to ~c √F. This is analogous to a situation where search hits (egg
 * intact) are much cheaper than misses (egg broken).
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class ThrowingTwoEggs {

    private final int f; // egg is broken if it is thrown off f or higher
    private final int[] floors;
    private int step;

    public ThrowingTwoEggs(int[] floors, int f) {
        this.floors = floors;
        this.f = f;
        this.step = firstStepIndex(floors.length);
    }

    public int findFloor() {
        int index = findFloorIndex(step - 1);
        return floors[index];
    }

    // ~ c * sqrt(f)
    public int findFloorIndex(int currentIndex) {
        if (currentIndex == floors.length - 2)
            if (isBroken(currentIndex))
                return currentIndex;
            else return ++currentIndex;

        if (isBroken(currentIndex))
            return findFloorIndexInGap(currentIndex - step, currentIndex);
        else
            return findFloorIndex(currentIndex + --step);
    }

    private int findFloorIndexInGap(int from, int to) {
        if (from != 0) from++;
        for (int i = from; i < to; i++)
            if (isBroken(i))
                return i;
        return to;
    }

    private boolean isBroken(int index) {
        return floors[index] >= f;
    }

    // You should solve such equation: n^2 + n - amountOfFloors = 0
    private int firstStepIndex(int amountOfFloors) {
        int discriminant = 1 + 8 * amountOfFloors;
        double n = (-1 + Math.sqrt(discriminant)) / 2.0;
        return (int) Math.ceil(n);
    }


    public static void main(String[] args) {
        int[] x = new int[100];
        for (int i = 0; i < 100; i++)
            x[i] = i + 1;

        for (int i = 0; i < 100; i++) {
            ThrowingTwoEggs eggs = new ThrowingTwoEggs(x, i + 1);
            int floor = eggs.findFloor();
            System.out.println(floor);
        }
    }
}
