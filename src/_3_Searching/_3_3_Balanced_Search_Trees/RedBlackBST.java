package _3_Searching._3_3_Balanced_Search_Trees;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;

import java.util.NoSuchElementException;

// TODO: 07.03.19 Revisit THIS
@SuppressWarnings("Duplicates")
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int n;
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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

//    public void put(Key key, Value value) {
//        root = put(root, key, value);
//        root.color = BLACK;
//    }
//
//    private Node put(Node h, Key key, Value value) {
//        if (h == null)
//            return new Node(key, value, 1, RED);
//
//        int cmp = key.compareTo(h.key);
//        if (cmp < 0) h.left = put(h.left, key, value);
//        else if (cmp > 0) h.right = put(h.right, key, value);
//        else h.val = value;
//
//        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
//        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
//        if (isRed(h.left) && isRed(h.right)) flipColors(h);
//
//        h.n = 1 + size(h.left) + size(h.right);
//        return h;
//    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
//        if (val == null) {
//            delete(key);
//            return;
//        }

        root = put(root, key, val);
        root.color = BLACK;
        // assert check();
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val   = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.n = size(h.left) + size(h.right) + 1;

        return h;
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public Key min() {
        return min(root);
    }

    private Key min(Node x) {
        if (x == null) return null;
        while (x.left != null)
            x = x.left;
        return x.key;
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        if (x == null) return null;
        while (x.right != null)
            x = x.right;
        return x.key;
    }

    public Iterable<Key> keys() {
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

    public Key select(int k) {
        Node x = select(root, k);
        if (x != null)
            return x.key;
        else
            return null;
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

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x != null)
            return x.key;
        else
            return null;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        Node prev = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) prev = x;
            if (cmp == 0) return x;
            else if (cmp > 0) x = x.right;
            else x = x.left;
        }

        return prev;
    }


    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x != null)
            return x.key;
        else
            return null;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        Node prev = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) prev = x;
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }

        return prev;
    }

    // ------------------------------------------------------------------------------------------------
//    private Node floor(Node x, Key key) {
//        if (x == null) return null;
//        int cmp = key.compareTo(x.key);
//        if (cmp == 0) return x;
//        if (cmp < 0) return floor(x.left, key);
//        Node t = floor(x.right, key);
//        if (t != null) return t;
//        else return x;
//    }

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


    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }


}
