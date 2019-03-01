package _3_Searching._3_2_Binary_Search_Trees.creative;

import _2_Sorting._2_3_Quicksort.Quick;
import _3_Searching._3_2_Binary_Search_Trees.BST;

/*****************************************************************************************************
 *
 * 3.2.25 Perfect balance. Write a program that inserts a set of keys into an initially emp-
 * ty BST such that the tree produced is equivalent to binary search, in the sense that the
 * sequence of compares done in the search for any key in the BST is the same as the se-
 * quence of compares used by binary search for the same set of keys.x
 *
 ****************************************************************************************************/
public class PerfectBalance {

    private static void perfect(BST<String, Integer> bst, String[] a) {
        Quick.sort(a);
        perfect(bst, a, 0, a.length - 1);
    }

    private static void perfect(BST<String, Integer> bst, String[] a, int lo, int hi) {
        if (hi < lo) return;
        int mid = lo + (hi - lo) / 2;
        bst.put(a[mid], mid);
        System.out.print(a[mid] + " ");
        perfect(bst, a, lo, mid - 1);
        perfect(bst, a, mid + 1, hi);
    }

    public static void main(String[] args) {
        String[] words = {"P", "E", "R", "F", "C", "T", "B", "I", "N", "A", "R", "Y", "S", "R", "H"};
        BST<String, Integer> bst = new BST<>();
        perfect(bst, words);
    }
}
