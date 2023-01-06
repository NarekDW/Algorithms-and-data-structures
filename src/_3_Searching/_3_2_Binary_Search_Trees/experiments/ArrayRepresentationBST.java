package _3_Searching._3_2_Binary_Search_Trees.experiments;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _3_Searching.SymbolTable;
import _3_Searching.SymbolTableTest;

/*****************************************************************************************************
 * <p>
 * 3.2.41 Array representation. Develop a BST implementation that represents the BST
 * with three arrays (preallocated to the maximum size given in the constructor): one with
 * the keys, one with array indices corresponding to left links, and one with array indices
 * corresponding to right links. Compare the performance of your program with that of
 * the standard implementation.
 *
 ****************************************************************************************************/
public class ArrayRepresentationBST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private final Key[] keys;
    private final Value[] values;
    private final int[] left;
    private final int[] right;
    private int n;

    public ArrayRepresentationBST() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public ArrayRepresentationBST(int size) {
        keys = (Key[]) new Comparable[size + 1];
        values = (Value[]) new Object[size + 1];
        left = new int[size + 1];
        right = new int[size + 1];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (n == 0) return null;

        int i = 1;
        while (i <= n) {
            if (i == 0) return null;
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) i = left[i];
            else if (cmp > 0) i = right[i];
            else return values[i];
        }

        return null;
    }

    @Override
    public void delete(Key key) {
        int i = 1;
        int deleteParent = 1;
        int deleteIndex = 0;
        boolean isLeftChild = true;
        while (i != 0) {
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) {
                isLeftChild = true;
                deleteParent = i;
                i = left[i];
            } else if (cmp > 0) {
                isLeftChild = false;
                deleteParent = i;
                i = right[i];
            } else {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == 0) return;

        if (right[deleteIndex] != 0) {
            delete(deleteIndex, right, left);
        } else if (left[deleteIndex] != 0) {
            delete(deleteIndex, left, right);
        } else {
            if (isLeftChild)
                left[deleteParent] = 0;
            else
                right[deleteParent] = 0;

            shiftArrays(deleteIndex);
            decreaseIndexes(deleteIndex);
            removeLast();
        }
    }

    /**
     * if right side array exists, then we set movingDirectionArray = right and oppositeDirectionArray = left,
     * and take the min element index from right side and substitute with the element we are going to delete.
     * otherwise, we set movingDirectionArray = left and oppositeDirectionArray = right and take the max
     * element index from left side and substitute with the element we are going to delete.
     */
    private void delete(int deleteIndex, int[] movingDirectionArray, int[] oppositeDirectionArray) {
        int substituteParentIndex = 0;
        int substituteIndex = movingDirectionArray[deleteIndex];
        boolean isTheSameDirection = true;
        while (substituteIndex < n && oppositeDirectionArray[substituteIndex] != 0) {
            isTheSameDirection = false;
            substituteParentIndex = substituteIndex;
            substituteIndex = oppositeDirectionArray[substituteIndex];
        }

        keys[deleteIndex] = keys[substituteIndex];
        keys[substituteIndex] = null;
        values[deleteIndex] = values[substituteIndex];
        values[substituteIndex] = null;

        if (substituteParentIndex != 0) {
            oppositeDirectionArray[substituteParentIndex] = 0;
        }

        if (isTheSameDirection) {
            movingDirectionArray[deleteIndex] = movingDirectionArray[substituteIndex];
        }

        shiftArrays(substituteIndex);
        decreaseIndexes(substituteIndex);
        removeLast();
    }

    private void shiftArrays(int indexFrom) {
        for (int k = indexFrom; k < n; k++) {
            keys[k] = keys[k + 1];
            values[k] = values[k + 1];
            left[k] = left[k + 1];
            right[k] = right[k + 1];
        }
    }

    private void decreaseIndexes(int guard) {
        for (int k = 1; k < n; k++) {
            if (left[k] > guard)
                left[k] = left[k] - 1;
            if (right[k] > guard)
                right[k] = right[k] - 1;
        }
    }

    private void removeLast() {
        keys[n] = null;
        values[n] = null;
        left[n] = 0;
        right[n] = 0;
        n--;
    }

    public void put(Key key, Value value) {
        int i = 1;

        if (n == 0) {
            n++;
            keys[n] = key;
            values[n] = value;
            return;
        }

        while (i <= n) {
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0) {
                int tmp = i;
                i = left[i];

                if (i == 0) {
                    n++;
                    keys[n] = key;
                    values[n] = value;
                    left[tmp] = n;
                    return;
                }
            } else if (cmp > 0) {
                int tmp = i;
                i = right[i];

                if (i == 0) {
                    n++;
                    keys[n] = key;
                    values[n] = value;
                    right[tmp] = n;
                    return;
                }
            } else {
                values[i] = value;
                return;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        int i = 1;
        while (i < n && left[i] != 0)
            i = left[i];
        return keys[i];
    }

    public Key max() {
        int i = 1;
        while (i < n && right[i] != 0)
            i = right[i];
        return keys[i];
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(1, queue, lo, hi);
        return queue;
    }

    private void keys(int x, Queue<Key> queue, Key lo, Key hi) {
        if (x == 0) return;
        int cmplo = lo.compareTo(keys[x]);
        int cmphi = hi.compareTo(keys[x]);
        if (cmplo < 0 && left[x] != 0) keys(left[x], queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(keys[x]);
        if (cmphi > 0 && right[x] != 0) keys(right[x], queue, lo, hi);
    }


    public static void main(String[] args) {
        SymbolTableTest.testST(new ArrayRepresentationBST<>());
    }
}
