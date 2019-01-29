package _1_Fundamentals._1_4_Analysis_of_Algorithms;

public class StringTest {

    public static void main(String[] args) {
        String a = "abcd";
        String s = "abcd";
        String b = a.substring(0, 2);
        String c = a.substring(0, 2);
        System.out.println(b == c);
        System.out.println(a == s);
    }

}
