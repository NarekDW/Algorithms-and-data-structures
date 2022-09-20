package _1_Fundamentals._1_1_Programming_Model.creative;

/*****************************************************************************************************
 *
 * 1.1.30 Array exercise.
 * Write a code fragment that creates an N-by-N boolean array a[][] such that
 * a[i][j] is true if i and j are relatively prime (have no common factors), and false otherwise.
 *
 ****************************************************************************************************/
public class ArrayExercise {

    public static void main(String[] args) {
        int n = 10;
        boolean[][] relativelyPrimeIndexes = relativelyPrimeIndexes(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(relativelyPrimeIndexes[i][j] + "[" + i + "][" + j + "]     ");
            }
            System.out.println();
        }

    }

    // 1.1.30
    public static boolean[][] relativelyPrimeIndexes(int n) {
        boolean[][] array = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    array[i][j] = false;
                } else {
                    int gcd = Euclid.gcd(i, j);
                    array[i][j] = gcd != 1;
                }
            }
        }
        return array;
    }

}
