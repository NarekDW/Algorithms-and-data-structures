package _1_Fundamentals._1_2_Data_Abstraction.exercises;

/*****************************************************************************************************
 *
 * 1.2.7 What does the following recursive function return?
 * Answer: it returns reversed input
 *
 ****************************************************************************************************/
public class ReverseString {

    public static void main(String[] args) {
        System.out.println(mystery("abcdXYZ"));
    }

    public static String mystery(String s) {
        int n = s.length();
        if (n <= 1) return s;
        String a = s.substring(0, n/2);
        String b = s.substring(n/2, n);
        return mystery(b) + mystery(a);
    }

}
