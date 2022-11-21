package _2_Sorting._2_4_Priority_Queues.creative;

import common.SortUtils;
import common.TripleNode;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/*****************************************************************************************************
 * <p>
 * 2.4.24 Priority queue with explicit links. Implement a priority queue using a
 * heap-ordered binary tree, but use a triply linked structure instead of an array.
 * You will need three links per node: two to traverse down the tree and one to
 * traverse up the tree. Your implementation should guarantee logarithmic running
 * time per operation, even if no maximum priority-queue size is known ahead of time.
 *
 ****************************************************************************************************/
public class LinkedListPQ<Key extends Comparable<Key>> {

    private TripleNode<Key> pq;
    private int n;

    // ~ 2log(n)
    public void insert(Key v) {
        TripleNode<Key> node = new TripleNode<>(v);
        if (pq != null) {
            TripleNode<Key> last = getLastRoot(pq);
            if (last.getLeft() == null)
                last.setLeft(node);
            else
                last.setRight(node);
            node.setParent(last);
        } else {
            pq = node;
        }

        n++;
        swim(node);
    }


    // ~ 2log(n)
    public Key delMax() {
        Key key = pq.getKey();
        if (n == 1) {
            n--;
            pq = null;
            return key;
        }

        TripleNode<Key> lastNode = getLastNode(pq);
        exch(lastNode, pq);
        removeNode(key, lastNode);

        sink(pq);
        n--;
        return key;
    }

    private void removeNode(Key key, TripleNode<Key> lastNode) {
        TripleNode<Key> parent = lastNode.getParent();
        if (parent.getLeft().getKey().compareTo(key) == 0)
            parent.setLeft(null);
        else
            parent.setRight(null);
        lastNode.setParent(null);
    }

    private void sink(TripleNode<Key> k) {
        while (k.getLeft() != null) {
            TripleNode<Key> node;
            if (k.getRight() != null && less(k.getLeft().getKey(), k.getRight().getKey()))
                node = k.getRight();
            else
                node = k.getLeft();

            if (less(node.getKey(), k.getKey())) break;
            exch(k, node);
            k = node;
        }
    }

    private void swim(TripleNode<Key> k) {
        while (k.getParent() != null && less(k.getParent().getKey(), k.getKey())) {
            exch(k.getParent(), k);
            k = k.getParent();
        }
    }

    private TripleNode<Key> getLastNode(TripleNode<Key> head) {
        int height = (int) (log(n) / log(2));
        return getLastNode(head, n, height);

    }

    private TripleNode<Key> getLastNode(TripleNode<Key> head, int n, int height) {
        if (n < 2)
            return head;

        int elementsOfFullTree = getAmountOfFullTreeElements(height);
        if (n == elementsOfFullTree) {
            n = elementsOfFullTree / 2;
            head = head.getRight();
            return getLastNode(head, n, height - 1);
        }

        int lowestLevelElements = getAmountOfElementsOnLowestLevel(height);
        int elementsOfTreeWithHalfAtLowestLevel = elementsOfFullTree - lowestLevelElements / 2;
        if (n <= elementsOfTreeWithHalfAtLowestLevel) {
            int half = (elementsOfFullTree - lowestLevelElements) / 2 + 1;
            n = n - half;
            return getLastNode(head.getLeft(), n, height - 1);
        } else {
            int half = elementsOfFullTree / 2 + 1;
            n = n - half;
            return getLastNode(head.getRight(), n, height - 1);
        }
    }

    private TripleNode<Key> getLastRoot(TripleNode<Key> head) {
        int height = (int) (log(n) / log(2));
        return getLastRoot(head, n, height);
    }

    private TripleNode<Key> getLastRoot(TripleNode<Key> head, int n, int height) {
        if (n < 3)
            return head;

        int elementsOfFullTree = getAmountOfFullTreeElements(height);
        if (n == elementsOfFullTree) {
            n = elementsOfFullTree / 2;
            head = head.getLeft();
            return getLastRoot(head, n, height - 1);
        }

        int lowestLevelElements = getAmountOfElementsOnLowestLevel(height);
        int elementsOfTreeWithHalfAtLowestLevel = elementsOfFullTree - lowestLevelElements / 2;
        if (n < elementsOfTreeWithHalfAtLowestLevel) {
            int half = (elementsOfFullTree - lowestLevelElements) / 2 + 1;
            n = n - half;
            return getLastRoot(head.getLeft(), n, height - 1);
        } else {
            int half = elementsOfFullTree / 2 + 1;
            n = n - half;
            return getLastRoot(head.getRight(), n, height - 1);
        }
    }

    private int getAmountOfFullTreeElements(int height) {
        return (int) ((1 - pow(2, height + 1)) / (-1));
    }

    private int getAmountOfElementsOnLowestLevel(int height) {
        return (int) pow(2, height);
    }

    private void exch(TripleNode<Key> n, TripleNode<Key> k) {
        Key tmp = n.getKey();
        n.setKey(k.getKey());
        k.setKey(tmp);
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        if (v == null)
            return true;
        else if (w == null)
            return false;
        else
            return v.compareTo(w) < 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }


    public static void main(String[] args) {
        LinkedListPQ<Integer> pq = new LinkedListPQ<>();
        Integer[] integers = SortUtils.generateArrayInteger(100_000);
        for (Integer i : integers)
            pq.insert(i);

        Integer prev = pq.delMax();
        while (!pq.isEmpty()) {
            Integer curr = pq.delMax();
            if (curr > prev)
                throw new RuntimeException();
            prev = curr;
        }
    }
}
