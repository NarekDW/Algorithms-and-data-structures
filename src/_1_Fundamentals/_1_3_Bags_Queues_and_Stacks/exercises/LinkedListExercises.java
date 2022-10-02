package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("ALL")
public class LinkedListExercises {
    public static final RuntimeException EXCEPTION = new RuntimeException("test failed");

    static class Node<T> implements Iterable<T> {
        private final T item;
        private Node next;

        public Node(T item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Node{item=" + item + ", next=" + next + '}';
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<>() {
                Node<T> tmp = Node.this;

                @Override
                public boolean hasNext() {
                    return tmp != null;
                }

                @Override
                public T next() {
                    T item = (T) tmp.item;
                    tmp = tmp.next;
                    return item;
                }
            };
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<T> node = (Node<T>) o;
            if (next == null && node.next == null)
                return item.equals(node.item);
            if (next == null || node.next == null)
                return false;
            return item.equals(node.item) && next.equals(node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item, next);
        }
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.19 Give a code fragment that removes the last node in a linked list whose first node is first.
     *
     ****************************************************************************************************/
    public static <T> Node<T> deleteLast(Node<T> node) {
        Node<T> tmp = node;
        if (tmp.next == null)
            return null;

        while (tmp.next.next != null)
            tmp = tmp.next;

        tmp.next = null;
        return node;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.20 Write a method delete() that takes an int argument k and deletes the k th ele-
     * ment in a linked list, if it exists.
     *
     ****************************************************************************************************/
    public static <T> Node<T> delete(int k, Node<T> node) {
        Node<T> tmp = node;

        if (k == 0)
            return node;
        if (k == 1)
            return node.next;

        for (int i = 1; i < k - 1; i++)
            if (tmp != null)
                tmp = tmp.next;

        if (tmp != null && tmp.next != null)
            tmp.next = tmp.next.next;

        return node;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.21 Write a method find() that takes a linked list and a string key as arguments
     * and returns true if some node in the list has key as its item field, false otherwise.
     *
     ****************************************************************************************************/
    public static <T> boolean find(T key, Node<T> node) {
        for (T t : node)
            if (t.equals(key))
                return true;
        return false;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.24 Write a method removeAfter() that takes a linked-list Node as argument and
     * removes the node following the given one (and does nothing if the argument or the next
     * field in the argument node is null).
     *
     ****************************************************************************************************/
    public static <T> Node<T> removeAfter(Node<T> node) {
        if (node.next != null)
            node.next = node.next.next;
        return node;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.25 Write a method insertAfter() that takes two linked-list Node arguments and inserts
     * the second after the first on its list (and does nothing if either argument is null).
     *
     ****************************************************************************************************/
    public static <T> Node<T> insertAfter(Node<T> first, Node<T> second) {
        if (first == null && second == null)
            return null;
        if (first == null)
            return second;
        if (second == null)
            return first;

        Node<T> tmp = first;
        while (tmp.next != null)
            tmp = tmp.next;

        tmp.next = second;
        return first;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.26 Write a method remove() that takes a linked list and a string key as arguments
     * and removes all of the nodes in the list that have key as its item field.
     *
     ****************************************************************************************************/
    public static <T> Node<T> remove(T key, Node<T> node) {
        Node<T> prev = null;
        Node<T> curr = node;
        while (curr != null) {
            if (curr.item.equals(key)) {
                if (prev == null) {
                    Node tmp = curr.next;
                    curr.next = null;
                    curr = tmp;
                    node = tmp;
                } else {
                    Node tmp = curr.next;
                    prev.next = tmp;
                    curr.next = null;
                    curr = tmp;
                }
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return node;
    }

    /*****************************************************************************************************
     * <p>
     * 1.3.27 Write a method max() that takes a reference to the first node in a linked list as
     * argument and returns the value of the maximum key in the list. Assume that all keys are
     * positive integers, and return 0 if the list is empty.
     *
     ****************************************************************************************************/
    public static Integer max(Node<Integer> node) {
        return max(node, node.item);
    }

    private static Integer max(Node<Integer> node, Integer max) {
        if (node == null) return max;
        if (node.item > max) return max(node.next, node.item);
        else return max(node.next, max);
    }

    /*****************************************************************************************************
     *
     * 1.3.30 Write a function that takes the first Node in a linked list as argument and (de-
     * structively) reverses the list, returning the first Node in the result.
     *
     ****************************************************************************************************/
    public static <T> Node<T> reverse(Node<T> node) {
        Node<T> first = node;
        Node<T> reverse = null;

        while (first != null) {
            Node<T> second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }

        return reverse;
    }

    public static <T> Node<T> reverseRecoursive(Node<T> first) {
        if (first == null) return null;
        if (first.next == null) return first;
        Node second = first.next;
        Node<T> rest = reverseRecoursive(second);
        second.next = first;
        first.next = null;
        return rest;
    }


    public static void main(String[] args) {
        testDeleteLast();
        testDeleteKth();
        testFind();
        testRemoveAfter();
        testInsertAfter();
        testRemove();
        testMax();
        testReverse();
    }

    private static void testReverse() {
        System.out.println("Running test for reverse function.");
        Node<Integer> node = generateNode(1, 5);
        Node<Integer> expected = new Node<>(4);
        expected.next = new Node(3);
        expected.next.next = new Node(2);
        expected.next.next.next = new Node(1);

        Node<Integer> reversed = reverse(node);
        if (!reversed.equals(expected))
            throw EXCEPTION;

        reversed = reverseRecoursive(generateNode(1, 5));
        if (!reversed.equals(expected))
            throw EXCEPTION;
    }

    private static void testMax() {
        System.out.println("Running test for max function.");
        Node<Integer> node = generateNode(0, 11);
        Integer max = max(node);
        if (max != 10)
            throw EXCEPTION;

        Node singleNode = new Node(11);
        max = max(singleNode);
        if (max != 11)
            throw EXCEPTION;

        node = new Node<>(1);
        node.next = new Node<>(3);
        node.next.next = new Node<>(2);
        max = max(node);
        if (max != 3)
            throw EXCEPTION;
    }

    private static void testRemove() {
        System.out.println("Running test for remove function.");
        Node<Integer> node = generateNode(0, 10);
        for (int i = 0; i < 10; i++) {
            Node<Integer> removed = remove(i, node);
            node = removed;
            Node<Integer> expected = generateNode(i + 1, 10);
            if (removed != null && expected != null && !removed.equals(expected))
                throw EXCEPTION;
        }

        node = generateNode(5, 10);
        Node<Integer> removed = remove(2, node);
        removed = remove(15, removed);
        if (!removed.equals(node))
            throw EXCEPTION;

        Node<Integer> expected = new Node<>(1);
        expected.next = new Node(3);
        node = generateNode(1, 4);
        removed = remove(2, node);
        if (!removed.equals(expected))
            throw EXCEPTION;

        node = generateNode(1, 11);
        removed = remove(10, node);
        expected = generateNode(1, 10);
        if (!removed.equals(expected))
            throw EXCEPTION;
    }

    private static void testInsertAfter() {
        System.out.println("Running test for insertAfter function.");
        Node<Integer> first = new Node<>(1);
        first.next = new Node(2);

        Node<Integer> second = new Node<>(11);
        second.next = new Node(22);

        Node<Integer> result = insertAfter(null, null);
        if (result != null)
            throw EXCEPTION;

        result = insertAfter(null, second);
        if (!result.equals(second))
            throw EXCEPTION;

        result = insertAfter(first, null);
        if (!result.equals(first))
            throw EXCEPTION;

        result = insertAfter(first, second);
        Node<Integer> expectedResult = new Node<>(1);
        expectedResult.next = new Node(2);
        expectedResult.next.next = new Node(11);
        expectedResult.next.next.next = new Node(22);
        if (!result.equals(expectedResult))
            throw EXCEPTION;
    }

    private static void testRemoveAfter() {
        System.out.println("Running test for removeAfter function.");
        Node<Integer> node = generateNode();
        Node<Integer> result = node;
        for (int i = 0; i < 7; i++)
            result = removeAfter(node);

        Node<Integer> expectedResult = new Node<>(0);
        expectedResult.next = new Node(8);
        expectedResult.next.next = new Node(9);
        if (!result.equals(expectedResult))
            throw EXCEPTION;

        Node singleNode = new Node(11);
        result = removeAfter(singleNode);
        if (!result.equals(singleNode))
            throw EXCEPTION;
    }

    private static void testFind() {
        System.out.println("Running test for find function.");
        if (find(13, generateNode()) ||
                !find(5, generateNode()) ||
                !find(2, generateNode()) ||
                find(20, generateNode())) {
            throw EXCEPTION;
        }
    }

    private static void testDeleteKth() {
        System.out.println("Running test for delete function.");
        for (int i = 1; i < 10; i++) {
            Node<Integer> deleteKth = delete(i, generateNode());
            if (find(i - 1, deleteKth))
                throw EXCEPTION;
        }

        Node<Integer> deleteKth0 = delete(0, generateNode());
        if (!deleteKth0.equals(generateNode()))
            throw EXCEPTION;

        Node<Integer> deleteKth20 = delete(20, generateNode());
        if (!deleteKth20.equals(generateNode()))
            throw EXCEPTION;
    }

    private static void testDeleteLast() {
        System.out.println("Running test for deleteLast function.");
        Node deletedLast = deleteLast(generateNode());
        if (find(9, deletedLast))
            throw EXCEPTION;

        Node singleNode = new Node(11);
        Node deletedLastSingleNode = deleteLast(singleNode);
        if (deletedLastSingleNode != null)
            throw EXCEPTION;
    }

    public static Node<Integer> generateNode() {
        return generateNode(0, 10);
    }

    public static Node<Integer> generateNode(int from, int to) {
        Node<Integer> node = null;
        Node<Integer> result = null;
        for (int i = from; i < to; i++) {
            if (node == null) {
                node = new Node<>(i);
                result = node;
            } else {
                node.next = new Node(i);
                node = node.next;
            }
        }
        return result;
    }

}
