package _3_Searching._3_2_Binary_Search_Trees;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _3_Searching.SymbolTable;
import _3_Searching.SymbolTableTest;

/*****************************************************************************************************
 * <p>
 * 3.2.13 Give nonrecursive implementations of get() and put() for BST.
 * <p>
 * 3.2.14 Give nonrecursive implementations of min(), max(), floor(), ceiling(), rank(), and select().
 *
 ****************************************************************************************************/
public class BSTNonRecursive<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private Node root;

    @Override
    public void put(Key key, Value val) {
        Node x = root;
        // If key exists, we change the value, but not the count(n)
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.value = val;
                return;
            }
        }

        x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            x.n = x.n + 1;
            if (cmp < 0) {
                if (x.left == null) {
                    x.left = new Node(key, val, 1);
                    return;
                }
                x = x.left;
            } else if (cmp > 0) {
                if (x.right == null) {
                    x.right = new Node(key, val, 1);
                    x.h = Math.max(computeHeight(x.left), computeHeight(x.right));
                    return;
                }
                x = x.right;
            }
        }

        root = new Node(key, val, 1);
    }

    private int computeHeight(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(computeHeight(x.left), computeHeight(x.right));
    }

    @Override
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public Key min() {
        Node min = min(root);
        if (min != null) {
            return min.key;
        } else return null;
    }

    private Node min(Node x) {
        if (x == null) return null;
        while (x.left != null) x = x.left;
        return x;
    }

    public Key max() {
        Node max = max(root);
        if (max != null) {
            return max.key;
        } else return null;
    }

    private Node max(Node x) {
        if (x == null) return null;
        while (x.right != null) x = x.right;
        return x;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x != null)
            return x.key;
        else return null;
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
        if (x != null)
            return x.key;
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

    public Key select(int k) {
        Node x = select(root, k);
        if (x != null)
            return x.key;
        else return null;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        while (x != null) {
            int t = size(x.left);
            if (t > k) x = x.left;
            else if (t < k) {
                x = x.right;
                k = k - t - 1;
            } else return x;
        }

        return null;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int res = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                res = res + 1 + size(x.left);
                x = x.right;
            } else return res + size(x.left);
        }

        return res;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public int internalPath() {
        return internalPath(root);
    }

    // The number of compares used for a search hit ending at a given node is 1 plus the depth.
    // Adding the depths of all nodes, we get a quantity known as the internal path length of the tree.
    private int internalPath(Node x) {
        if (x == null) return 0;
        if (x.left == null && x.right == null) return 0;
        return internalPath(x.left) + internalPath(x.right) + 1;
    }

    public int externalPath() {
        return externalPath(root);
    }

    private int externalPath(Node x) {
        if (x == null) return 0;
        return externalPath(x.left) + externalPath(x.right) + x.n - 1;
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

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
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

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.n;
    }

    @Override
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

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int n, h;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }

        @Override
        public String toString() {
            return "Node{k=" + key + ", v=" + value + ", l=" + left + ", r=" + right + '}';
        }
    }


    public static void main(String[] args) {
        BSTNonRecursive<Integer, String> bst1 = new BSTNonRecursive<>();
        SymbolTableTest.testST(bst1);
    }
}
