package _2_Sorting._2_3_Quicksort.creative;

/*****************************************************************************************************
 *
 * 2.3.22 Fast 3-way partitioning. ( J. Bentley and D. McIlroy) Implement an entropy-
 * optimal sort based on keeping item's with equal keys at both the left and right ends
 * of the subarray. Maintain indices p and q such that a[lo..p-1] and a[q+1..hi] are all equal to a[lo] ,
 * an index i such that a[p..i-1] are all less than a[lo] , and an index j such that a[j+1..q] are all
 * greater than a[lo] . Add to the inner partitioning loop code to swap a[i] with a[p] (and increment p )
 * if it is equal to v and to swap a[j] with a[q] (and decrement q ) if it is equal to v before the usual
 * comparisons of a[i] and a[j] with v . After the partitioning loop has terminated, add code to swap
 * the items with equal keys into position.
 *
 * Note : This code complements the code given in the text, in the sense that it does extra swaps for keys
 * equal to the partitioning item’s key, while the code in the text does extra swaps for keys that are
 * not equal to the partitioning item’s key.
 *
 ****************************************************************************************************/
public class QuickBentleyMcIlroy {
}
