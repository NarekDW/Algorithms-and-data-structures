package _3_Searching._3_5_Searching_Applications.experiments;

import common.Stopwatch;
import common.StdRandom;

/******************************************************************************
 *  Compilation:  javac SparseMatrix.java
 *  Execution:    java SparseMatrix
 *  Dependencies: StdOut.java
 *
 *  A sparse, square matrix, implementing using two arrays of sparse
 *  vectors, one representation for the rows and one for the columns.
 *
 *  For matrix-matrix product, we might also want to store the
 *  column representation.
 *
 ******************************************************************************/

/*****************************************************************************************************
 *
 * 3.5.34 Sparse vector. Run experiments to compare the performance of matrix-vector
 * multiplication using SparseVector to the standard implementation using arrays.
 *
 ****************************************************************************************************/
public class SparseMatrix {
    private int n;                 // n-by-n matrix
    private SparseVector[] rows;   // the rows, each row is a sparse vector

    // initialize an n-by-n matrix of all 0s
    public SparseMatrix(int n) {
        this.n = n;
        rows = new SparseVector[n];
        for (int i = 0; i < n; i++)
            rows[i] = new SparseVector(n);
    }

    // put A[i][j] = value
    public void put(int i, int j, double value) {
        rows[i].put(j, value);
    }

    // return A[i][j]
    public double get(int i, int j) {
        return rows[i].get(j);
    }

    // return the number of nonzero entries (not the most efficient implementation)
    public int nnz() {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += rows[i].nnz();
        return sum;
    }

    // return the matrix-vector product b = Ax
    public SparseVector times(SparseVector x) {
        if (n != x.size()) throw new IllegalArgumentException("Dimensions disagree");
        SparseVector b = new SparseVector(n);
        for (int i = 0; i < n; i++)
            b.put(i, rows[i].dot(x));
        return b;
    }

    public static double[] times(Double[][] a, Double[] x) {
        int n = x.length;
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += a[i][j] * x[j];
            }

            b[i] = sum;
        }

        return b;
    }

    // return this + that
    public SparseMatrix plus(SparseMatrix that) {
        if (this.n != that.n) throw new RuntimeException("Dimensions disagree");
        SparseMatrix result = new SparseMatrix(n);
        for (int i = 0; i < n; i++)
            result.rows[i] = this.rows[i].plus(that.rows[i]);
        return result;
    }


    // return a string representation
    public String toString() {
        String s = "n = " + n + ", nonzeros = " + nnz() + "\n";
        for (int i = 0; i < n; i++) {
            s += i + ": " + rows[i] + "\n";
        }
        return s;
    }


    // test client
    public static void main(String[] args) {
//        SparseMatrix A = new SparseMatrix(5);
//        SparseVector x = new SparseVector(5);
//        A.put(0, 0, 1.0);
//        A.put(1, 1, 1.0);
//        A.put(2, 2, 1.0);
//        A.put(3, 3, 1.0);
//        A.put(4, 4, 1.0);
//        A.put(2, 4, 0.3);
//        x.put(0, 0.75);
//        x.put(2, 0.11);
//        StdOut.println("x     : " + x);
//        StdOut.println("A     : " + A);
//        StdOut.println("Ax    : " + A.times(x));
//        StdOut.println("A + A : " + A.plus(A));


        Stopwatch stopwatch = new Stopwatch();
        int s = 8_000;

        /*
        SparseMatrix
        dimension = 8000
        Time = 3.716

        Matrix
        dimension = 8000
        Time = 10.202
         */
        SparseMatrix matrix = new SparseMatrix(s);
        SparseVector vector = new SparseVector(s);

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (j % 100 == 0)
                    matrix.put(i, j, StdRandom.uniform());
                else
                    matrix.put(i, j, 0.0);
            }
        }

        for (int i = 0; i < s; i++) {
            if (i % 100 == 0)
                vector.put(i, StdRandom.uniform());
            else
                vector.put(i, 0.0);
        }

        SparseVector times = matrix.times(vector);
        System.out.println("dimension = " + times.dimension());
        double time = stopwatch.elapsedTime();
        System.out.println("Time = " + time);


        Stopwatch stopwatch2 = new Stopwatch();
        Double[][] matrix2 = new Double[s][s];
        Double[] vector2 = new Double[s];

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (j % 100 == 0)
                    matrix2[i][j] = StdRandom.uniform();
                else
                    matrix2[i][j] = 0.0;
            }
        }

        for (int i = 0; i < s; i++) {
            if (i % 100 == 0)
                vector2[i] = StdRandom.uniform();
            else
                vector2[i] = 0.0;
        }

        double[] times2 = SparseMatrix.times(matrix2, vector2);
        System.out.println("dimension = " + times2.length);
        double time2 = stopwatch2.elapsedTime();
        System.out.println("Time = " + time2);
    }

}

