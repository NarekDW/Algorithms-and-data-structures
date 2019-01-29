package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

/*****************************************************************************************************
 *
 * 1.4.24 Throwing eggs from a building. Suppose that you have an N-story building and
 * plenty of eggs. Suppose also that an egg is broken if it is thrown off floor F or higher,
 * and unhurt otherwise. First, devise a strategy to determine the value of F such that the
 * number of broken eggs is ~lg N when using ~lg N throws, then find a way to reduce the
 * cost to ~2lg F.
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class ThrowingEggs {

    private int floor;

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


    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < 11; i++) {
            ThrowingEggs eggs = new ThrowingEggs(i);
            int floor = eggs.findFloor(x);
            int floorFast = eggs.findFloorFast(x);
            System.out.print("i = " + i + " ");
            System.out.println(floor + " : " + floorFast);
        }

    }
}
