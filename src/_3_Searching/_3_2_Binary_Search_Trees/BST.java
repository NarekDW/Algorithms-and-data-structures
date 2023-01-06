package _3_Searching._3_2_Binary_Search_Trees;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _3_Searching.SymbolTable;
import common.StdOut;
import common.StdRandom;
import common.Tuple2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private Node root;

    /*****************************************************************************************************
     * 3.2.28 Software caching.
     ****************************************************************************************************/
    private Node last;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int n;
        int h; // height
        int p;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }

        @Override
        public String toString() {
            return "{k=" + key + ", v=" + value + ", l=" + left + ", r=" + right + '}';
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return size(x.left) + size(x.right) + 1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Recursive solution
    public Value get(Key key) {
        if (last != null && last.key.compareTo(key) == 0)
            return last.value;
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
        if (last != null && last.key.compareTo(key) == 0)
            last.value = value;
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.n = size(x.left) + size(x.right) + 1;
        x.h = 1 + Math.max(computeHeight(x.left), computeHeight(x.right));
        x.p = internalPath(x.left) + internalPath(x.right) + 1;
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

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x != null) {
            last = x;
            return x.key;
        } else return null;
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

    /*****************************************************************************************************
     * <p>
     * 3.2.16 Define the external path length of a tree to be the sum of the number of nodes on
     * the paths from the root to all null links. Prove that the difference between the external
     * and internal path lengths in any binary tree with N nodes is 2N (see Proposition C).
     *
     ****************************************************************************************************/
    public int externalPath() {
        return externalPath(root);
    }


    private int externalPath(Node x) {
        if (x == null) return 0;
        return externalPath(x.left) + externalPath(x.right) + x.n - 1;
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
     * <p>
     * 3.2.6 Add to BST a method height() that computes the height of the tree. Develop two
     * implementations: a recursive method (which takes linear time and space proportional
     * to the height), and a method like size() that adds a field to each node in the tree (and
     * takes linear space and constant time per query).
     *
     ****************************************************************************************************/
    public int height() {
        return root.h;
    }

    public int computeHeight() {
        return computeHeight(root);
    }

    private int computeHeight(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(computeHeight(x.left), computeHeight(x.right));
    }

    /*****************************************************************************************************
     * <p>
     * 3.2.7 Add to BST a recursive method avgCompares() that computes the average number of
     * compares required by a random search hit in a given BST (the internal path length of the
     * tree divided by its size, plus one). Develop two implementations: a recursive method
     * (which takes linear time and space proportional to the height), and a method like size()
     * that adds a field to each node in the tree (and takes linear space and constant time per query).
     *
     ****************************************************************************************************/
    public int avgCompares() {
        return root.p / root.h + 1;
    }

    public int computeAvgCompares() {
        return internalPath(root) / computeHeight(root) + 1;
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
     * <p>
     * 3.2.29 Binary tree check. Write a recursive method isBinaryTree() that takes a Node
     * as argument and returns true if the subtree count field N is consistent in the data
     * structure rooted at that node, false otherwise. Note : This check also ensures that
     * the data structure has no cycles and is therefore a binary tree (!).
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
     * <p>
     * 3.2.30 Order check. Write a recursive method isOrdered() that takes a Node and two
     * keys min and max as arguments and returns true if all the keys in the tree are between
     * min and max ; min and max are indeed the smallest and largest keys in the tree,
     * respectively; and the BST ordering property holds for all keys in the tree; false otherwise.
     *
     ****************************************************************************************************/
    public boolean isOrdered(Key min, Key max) {
        return isOrdered(root, min, max);
    }

    private boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) < 0) return false;
        if (max != null && x.key.compareTo(max) > 0) return false;
        return isOrdered(x.left, min, x.key) && isOrdered(x.right, x.key, max);
    }

    /*****************************************************************************************************
     * <p>
     * 3.2.31 Equal key check. Write a method hasNoDuplicates() that takes a Node as argument and
     * returns true if there are no equal keys in the binary tree rooted at the argument node, false
     * otherwise. Assume that the test of the previous exercise has passed.
     *
     ****************************************************************************************************/
    public boolean hasNoDuplicates(Node node) {
        return hasNoDuplicates(node, root);
    }

    private boolean hasNoDuplicates(Node node, Node root) {
        if (node == null || root == null) return true;
        Key nodeKey = node.key;
        Key rootKey = root.key;
        int cmp = nodeKey.compareTo(rootKey);
        if (cmp == 0) return false;
        if (cmp < 0) return hasNoDuplicates(node, root.left) && hasNoDuplicates(node.right, root);
        return hasNoDuplicates(node.left, root) && hasNoDuplicates(node, root.right);
    }

    /*****************************************************************************************************
     * <p>
     * 3.2.32 Certification. Write a method isBST() that takes a Node as argument and returns true
     * if the argument node is the root of a binary search tree, false otherwise.
     * Hint : This task is also more difficult than it might seem, because the order in which you
     * call the methods in the previous three exercises is important.
     *
     ****************************************************************************************************/
    private boolean isBST() {
        if (!isBinaryTree()) return false;
        if (!isOrdered(root, min(), max())) return false;
        return !hasNoDuplicates(root);
    }

    /*****************************************************************************************************
     * <p>
     * 3.2.37 Level-order traversal. Write a method printLevel() that takes a Node as argument and
     * prints the keys in the subtree rooted at that node in level order (in order of their distance
     * from the root, with nodes on each level in order from left to right).
     * Hint : Use a Queue.
     *
     ****************************************************************************************************/
    public void printLevel(Node node) {
        Collection<Tuple2<Key, Integer>> level = collectLevel(node, 1, new ArrayList<>());
        Map<Integer, List<Key>> map = level.stream()
                .collect(Collectors.toMap(Tuple2::getY,
                        o -> Collections.singletonList(o.getX()),
                        (l1, l2) -> {
                            ArrayList<Key> res = new ArrayList<>(l1);
                            res.addAll(l2);
                            res.sort(Comparable::compareTo);
                            return res;
                        }));

        for (Map.Entry<Integer, List<Key>> entry : map.entrySet()) {
            List<Key> keys = entry.getValue();
            keys.forEach(key -> System.out.print(key + " "));
            System.out.println();
        }
    }

    public Collection<Tuple2<Key, Integer>> collectLevel(Node node, int level,
                                                         Collection<Tuple2<Key, Integer>> result) {
        if (node == null)
            return result;
        result.add(new Tuple2<>(node.key, level++));
        collectLevel(node.left, level, result);
        collectLevel(node.right, level, result);
        return result;
    }

    // Non-recursive !!!
    public void printLevel2(Node node) {
        if (node == null) return;
        int lvl = 1;
        Map<Integer, List<Node>> map = new HashMap<>();
        map.put(lvl++, List.of(node));

        List<Node> nodes = List.of(node);
        while (true) {
            nodes = nodes.stream().flatMap(n -> Stream.of(n.left, n.right))
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing((Node o) -> o.key))
                    .collect(Collectors.toList());

            if (nodes.isEmpty()) break;
            map.put(lvl++, nodes);
        }

        for (Map.Entry<Integer, List<Node>> entry : map.entrySet()) {
            List<Node> keys = entry.getValue();
            keys.forEach(n -> System.out.print(n.key + " "));
            System.out.println();
        }
    }


    public static void main(String[] args) {
        testHeight();
        testAvgCompares();
        testHasNoDuplicates();
        testIsBST();
        testPrintLevel();
    }

    private static void testPrintLevel() {
        BST<Integer, Integer> bst = new BST<>();
        int testValue = -1;
        bst.put(5, testValue);
        bst.put(4, testValue);
        bst.put(3, testValue);
        bst.put(2, testValue);
        bst.put(0, testValue);
        bst.put(7, testValue);
        bst.put(6, testValue);
        bst.put(9, testValue);
        bst.put(11, testValue);
        System.out.println("Recursive implementation of print levels:");
        bst.printLevel(bst.root);
        System.out.println("\nNon-Recursive implementation of print levels:");
        bst.printLevel2(bst.root);
    }

    private static void testIsBST() {
        BST<Integer, Integer> bst = createRandomBST();
        if (!bst.isBST())
            throw new RuntimeException();
    }

    private static void testHasNoDuplicates() {
        BST<Integer, Integer> bst = new BST<>();
        int testValue = -1;
        bst.put(5, testValue);
        bst.put(4, testValue);
        bst.put(3, testValue);
        bst.put(2, testValue);
        bst.put(0, testValue);
        bst.put(7, testValue);
        bst.put(6, testValue);
        bst.put(9, testValue);
        bst.put(11, testValue);

        BST<Integer, Integer> bst2 = new BST<>();
        bst2.put(55, testValue);
        bst2.put(44, testValue);
        bst2.put(33, testValue);
        bst2.put(22, testValue);
        bst2.put(-1, testValue);
        bst2.put(77, testValue);
        bst2.put(66, testValue);
        bst2.put(99, testValue);
        bst2.put(11, testValue);

        if (bst.hasNoDuplicates(bst2.root))
            throw new RuntimeException();

        BST<Integer, Integer> bst3 = new BST<>();
        bst3.put(55, testValue);
        bst3.put(44, testValue);
        bst3.put(33, testValue);
        bst3.put(22, testValue);
        bst3.put(-1, testValue);
        bst3.put(77, testValue);
        bst3.put(66, testValue);
        bst3.put(99, testValue);
        bst3.put(111, testValue);

        if (!bst.hasNoDuplicates(bst3.root))
            throw new RuntimeException();
    }

    private static void testHeight() {
        for (int j = 0; j < 100; j++) {
            BST<Integer, Integer> bst = createRandomBST();
            if (bst.height() != bst.computeHeight()) throw new RuntimeException();
        }
    }

    private static void testAvgCompares() {
        for (int j = 0; j < 100; j++) {
            BST<Integer, Integer> bst = createRandomBST();
            if (bst.avgCompares() != bst.computeAvgCompares()) throw new RuntimeException();
        }
    }

    private static BST<Integer, Integer> createRandomBST() {
        BST<Integer, Integer> bst = new BST<>();
        for (int i = 0; i < 100; i++) {
            int x = StdRandom.uniform(100);
            bst.put(x, x);
        }
        return bst;
    }
}
