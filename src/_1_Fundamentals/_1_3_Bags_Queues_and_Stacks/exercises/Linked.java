package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

@SuppressWarnings("ALL")
public class Linked<Item> {

    static class Node<T> {

        public Node(T item) {
            this.item = item;
        }

        T item;
        Node next;

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }


    /*****************************************************************************************************
     *
     * 1.3.20 Write a method delete() that takes an int argument k and deletes the k th ele-
     * ment in a linked list, if it exists.
     *
     ****************************************************************************************************/
    public static <T> Node<T> delete(int k, Node<T> node) {
        Node<T> tmp = node;

        for (int i = 0; i < k - 1; i++) {
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
        return node;
    }

    /*****************************************************************************************************
     *
     * 1.3.21 Write a method find() that takes a linked list and a string key as arguments
     * and returns true if some node in the list has key as its item field, false otherwise.
     *
     ****************************************************************************************************/
    public static <T> boolean find(T key, Node<T> node) {
        while (node != null) {
            if (node.item == key) return true;
            node = node.next;
        }

        return false;
    }

    /*****************************************************************************************************
     *
     * 1.3.24 Write a method removeAfter() that takes a linked-list Node as argument and
     * removes the node following the given one (and does nothing if the argument or the next
     * field in the argument node is null).
     *
     ****************************************************************************************************/
    public static <T> void removeAfter(Node<T> node) {
        if (node.next != null) {
            node.next = node.next.next;
        }
    }

    /*****************************************************************************************************
     *
     * 1.3.26 Write a method remove() that takes a linked list and a string key as arguments
     * and removes all of the nodes in the list that have key as its item field.
     *
     ****************************************************************************************************/
    public static <T> Node<T> remove(T key, Node<T> node) {
        while (node.item.equals(key)) {
            Node<T> s = node.next;
            node.next = null;
            node = s;
        }

        Node<T> tmp = node;
        Node<T> prev = node;
        node = node.next;
        while (node != null) {
            if (node.item.equals(key)) {
                prev.next = node.next;
                Node<T> s = node.next;
                node.next = null;
                node = s;
            } else {
                prev = node;
                node = node.next;
            }
        }

        return tmp;
    }

//    public static Integer max(Node<Integer> node) {
//        int max;
//        if (node == null)
//            return 0;
//        else
//            max = node.item;
//        node = node.next;
//
//        while (node != null) {
//            if (node.item > max)
//                max = node.item;
//            node = node.next;
//        }
//
//        return max;
//    }

    /*****************************************************************************************************
     *
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

    public static <T> Node<T> reverseR(Node<T> first) {
        if (first == null) return null;
        if (first.next == null) return first;
        Node second = first.next;
        Node<T> rest = reverseR(second);
        second.next = first;
        first.next = null;
        return rest;
    }


    public static void main(String[] args) {
        Node<Integer> first = new Node<>(1);
        Node<Integer> x1 = new Node<>(1);
        Node<Integer> x2 = new Node<>(2);
        Node<Integer> x3 = new Node<>(7);
        Node<Integer> x4 = new Node<>(4);
        Node<Integer> x5 = new Node<>(5);

        first.next = x1;
        x1.next = x2;
        x2.next = x3;
        x3.next = x4;
        x4.next = x5;

        System.out.println(first);
        System.out.println("reverse: " + reverse(first));

//        System.out.println("max(first) : " + max(first));
//        System.out.println("remove(1, first) : " + remove(1, first));
//        System.out.println(first);


//        System.out.println("find 3: " + find(3, first));
//        System.out.println("find 30: " + find(30, first));
//        System.out.println("delete: " + delete(2, first));

//        removeAfter(first);
//        System.out.println("removeAfter(first) : " + first);

//        removeAfter(x4);
//        System.out.println("removeAfter(first) : " + first);


//        Node<Integer> z = first;
//        while (z.next.next != null) {
//            z = z.next;
//        }
//
//        z.next = null;
//
//        System.out.println(first);
    }

}
