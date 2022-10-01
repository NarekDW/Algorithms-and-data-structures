package _1_Fundamentals._1_2_Data_Abstraction.exercises;

/*****************************************************************************************************
 * <p>
 * 1.2.6 A string s is a circular rotation of a string t if it matches when the characters
 * are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift of
 * TGACGAC , and vice versa. Detecting this condition is important in the study of genomic
 * sequences. Write a program that checks whether two given strings s and t are circular
 * shifts of one another. Hint : The solution is a one-liner with indexOf() , length() , and
 * string concatenation.
 *
 ****************************************************************************************************/
public class StringCircularRotation {

    public static void main(String[] args) {
        System.out.println(isCircularRotation("ACTGACG", "TGACGAC"));
        System.out.println(isCircularRotation("ACTGACG", "TGACGAX"));
        System.out.println(isCircularRotation("ACTGACG", "TGACGAXasd"));
    }

    public static boolean isCircularRotation(String s, String t) {
        return s.length() == t.length() && s.concat(s).contains(t);
    }

}
