package _3_Searching._3_5_Searching_Applications.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import common.StdOut;

/*****************************************************************************************************
 * <p>
 * 3.5.9 Modify BST to keep duplicate keys in the tree. Return any value associated with
 * the given key for get() , and remove all nodes in the tree that h ave keys equal to the
 * given key for delete() .
 *
 ****************************************************************************************************/
public class BSTWithDuplicates<Key extends Comparable<Key>, Value> {
    private Node root;
    private Node last;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int n;
        int h;
        int p;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.n;
    }

    public boolean isEmpty() {
        return root == null;
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else x.right = put(x.right, key, value);
        x.n = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height(x.left), height(x.right));
        x.p = internalPath(x.left) + internalPath(x.right) + x.n - 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else return max(x.right);
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key floor2(Key key) {
        return floor2(root, key, null);
    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor2(x.left, key, best);
        else if (cmp > 0) return floor2(x.right, key, x.key);
        else return x.key;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x != null) {
            last = x;
            return x.key;
        }
        return null;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        Node x = select(root, k);
        if (x != null) {
            last = x;
            return x.key;
        } else return null;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Value getLastValue() {
        return last.value;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        while (contains(key))
            root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /*****************************************************************************************************
     *
     * 3.2.6 Add to BST a method height() that computes the height of the tree. Develop two
     * implementations: a recursive method (which takes linear time and space proportional
     * to the height), and a method like size() that adds a field to each node in the tree (and
     * takes linear space and constant time per query).
     *
     ****************************************************************************************************/
    public int height() {
        return root.h;
    }

//    public int height() {
//        return height(root);
//    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /*****************************************************************************************************
     *
     * 3.2.7 Add to BST a recursive method avgCompares() that computes the average num-
     * ber of compares required by a random search hit in a given BST (the internal path
     * length of the tree divided by its size, plus one). Develop two implementations: a re-
     * cursive method (which takes linear time and space proportional to the height), and a
     * method like size() that adds a field to each node in the tree (and takes linear space and
     * constant time per query).
     *
     ****************************************************************************************************/
    public int avgCompares() {
        return root.p / root.n + 1;
    }

//    public int avgCompares() {
//        return internalPath(root) / root.n + 1;
//    }

    public int internalPath() {
        return internalPath(root);
    }

    // ???
    private int internalPath(Node x) {
        if (x == null) return 0;
        if (x.left == null && x.right == null) return 0;
        return internalPath(x.left) + internalPath(x.right) + 1;
    }

//    private int internalPath(Node x) {
//        if (x == null) return 0;
//        return internalPath(x.left) + internalPath(x.right) + x.n - 1;
//    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Key x) {
        return get(x) != null;
    }

    /*****************************************************************************************************
     *
     * 3.2.29 Binary tree check. Write a recursive method isBinaryTree() that takes a Node
     * as argument and returns true if the subtree count field N is consistent in the data struc-
     * ture rooted at that node, false otherwise. Note : This check also ensures that the data
     * structure has no cycles and is therefore a binary tree (!).
     *
     ****************************************************************************************************/
    public boolean isBinaryTree() {
        return count(root, 0) == root.n;
    }

    private int count(Node x, int cnt) {
        if (x == null) return cnt;
        return count(x.right, 1 + count(x.left, cnt));
    }

    /*****************************************************************************************************
     *
     * 3.2.30 Order check. Write a recursive method isOrdered() that takes a Node and two
     * keys min and max as arguments and returns true if all the keys in the tree are between
     * min and max ; min and max are indeed the smallest and largest keys in the tree, respec-
     * tively; and the BST ordering property holds for all keys in the tree; false otherwise.
     *
     ****************************************************************************************************/
    public boolean isOrdered(Key min, Key max) {
        return isOrdered(root, min, max);
    }

    private boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isOrdered(x.left, min, x.key) && isOrdered(x.right, x.key, max);
    }


    public static void main(String[] args) {
        BSTWithDuplicates<Integer, String> st = new BSTWithDuplicates<>();

        st.put(1, "a");
        st.put(2, "b");
        st.put(2, "c");
        st.put(2, "d");
        st.put(3, "e");
        st.put(3, "f");
        st.put(4, "g");

        st.keys().forEach(System.out::println);
        st.delete(2);
        System.out.println();
        st.keys().forEach(System.out::println);
        st.delete(3);
        System.out.println();
        st.keys().forEach(System.out::println);
    }
}
