package _1_Fundamentals._1_2_Data_Abstraction.creative;

/*****************************************************************************************************
 * <p>
 * 1.2.18 Variance for accumulator.
 * Variance, mean and deviation for streaming application.
 *
 ****************************************************************************************************/
public class MyAccumulator {

    private double sum = 0;
    private double sumSqr = 0;
    private int n = 0;

    public void addDataValue(double x) {
        n++;
        sum += x;
        sumSqr += Math.pow(x, 2);
    }

    /***
     * mean(x) = sum (xi) [i = 1 ... n] / n
     */
    public double mean() {
        return sum / n;
    }

    /***
     * var = sum (xi - mean)^2 / n
     *     = sum (xi^2) / n - (2 * mean * sum (xi)) / n + sum (mean^2) / n
     *     = mean(x^2) - 2 * mean * mean + mean^2
     *     = mean(x^2) - mean^2
     */
    public double variance() {
        return sumSqr / n - Math.pow(mean(), 2);
    }

    public double deviation() {
        return Math.sqrt(variance());
    }


    public static void main(String[] args) {
        MyAccumulator stats = new MyAccumulator();
        double[] values = {2, 7, 3, 12, 9};
        System.out.printf("%12s : %12s : %12s\n", "Mean", "Variance", "Deviation");
        for (double value : values) {
            stats.addDataValue(value);
            System.out.printf("%12.2f : %12.2f : %12.2f\n", stats.mean(), stats.variance(), stats.deviation());
        }
    }

}
