package _1_Fundamentals._1_1_Programming_Model;

public class Generator {

    public static void main(String[] args) {
        final int N = Integer.parseInt(args[0]);
        for (int i = 0; i < N; i++) {
            System.out.println(i);
        }
    }

}
