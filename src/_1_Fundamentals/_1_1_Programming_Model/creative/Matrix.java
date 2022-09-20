package _1_Fundamentals._1_1_Programming_Model.creative;

/*****************************************************************************************************
 * <p>
 * 1.1.33 Matrix library. Write a library Matrix that implements the following API:
 * <p>
 * public class Matrix
 *      static double dot(double[] x, double[] y) vector dot product
 *      static double[][] mult(double[][] a, double[][] b) matrix-matrix product
 *      static double[][] transpose(double[][] a) transpose
 *      static double[] mult(double[][] a, double[] x) matrix-vector product
 *      static double[] mult(double[] y, double[][] a) vector-matrix product
 * <p>
 * Develop a test client that reads values from standard input and tests all the methods.
 *
 ****************************************************************************************************/
public class Matrix {

    public static void main(String[] args) {
        double[] x = {2.0, -5.0};
        double[] y = {-1.0, 0.0};
        double dot = dot(x, y);
//        System.out.println("dot = " + dot);

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
//        displayArray(mult);

        double[][] transpose = transpose(a);
//        displayArray(transpose);

        double[][] aa = {{1}, {2}, {3}};
        double[] bb = {4, 5};
        double[][] mult2 = mult(aa, bb);
//        displayArray(mult2);

        double[] aaa = {1, 2, 3};
        double[][] bbb = {{4, 5, 6}, {6, 7, 8}, {9, 10, 11}};
        double[] mult3 = mult(aaa, bbb);
//        displayArray(mult3);
    }

    static void displayArray(double[][] t) {
        int m = t[0].length;
        for (double[] ts : t) {
            for (int j = 0; j < m; j++)
                System.out.print(ts[j] + "   ");
            System.out.println();
        }
    }

    static void displayArray(double[] t) {
        for (double v : t) System.out.print(v + "   ");
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

    static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] result = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                result[j][i] = a[i][j];
            }
        return result;
    }

    static double[][] mult(double[][] a, double[] x) {
        double[][] res = new double[a.length][x.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < x.length; j++) {
                res[i][j] = a[i][0] * x[j];
            }
        }
        return res;
    }

    static double[] mult(double[] y, double[][] a) {
        double[] res = new double[a[0].length];
        for (int i = 0; i < a[0].length; i++) {
            double sum = 0.0;
            for (int j = 0; j < y.length; j++) {
                sum += y[j] * a[j][i];
            }
            res[i] = sum;
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
