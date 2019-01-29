package _1_Fundamentals._1_1_Programming_Model.creative;

/*****************************************************************************************************
 *
 * 1.1.33 Matrix library. Write a library Matrix that implements the following API:
 *
 * public class Matrix
 *      static double dot(double[] x, double[] y) vector dot product
 *      static double[][] mult(double[][] a, double[][] b) matrix-matrix product
 *      static double[][] transpose(double[][] a) transpose
 *      static double[] mult(double[][] a, double[] x) matrix-vector product
 *      static double[] mult(double[] y, double[][] a) vector-matrix product
 *
 * Develop a test client that reads values from standard input and tests all the methods.
 *
 ****************************************************************************************************/
public class Matrix {

    public static void main(String[] args) {
        double[] x = {2.0, -5.0};
        double[] y = {-1.0, 0.0};
        double dot = dot(x, y);
        System.out.println("dot = " + dot);


        double[][] a = {
                {1, 2, 2},
                {3, 1, 1}
        };

        double[][] b = {
                {4, 2},
                {3, 1},
                {1, 5}
        };
        double[][] mult = mult(a, b);
        for (double[] row : mult) {
            for (double el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    static double[][] mult(double[][] a, double[][] b) {
        double[][] res = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                double sum = 0;
                for (int k = 0; k < b.length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                res[i][j] = sum;
            }
        }

        return res;
    }

    static double dot(double[] x, double[] y) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }
        return sum;
    }

}
