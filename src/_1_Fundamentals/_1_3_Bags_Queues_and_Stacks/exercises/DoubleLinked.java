package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

/*****************************************************************************************************
 * <p>
 * 1.3.31 Implement a nested class DoubleNode for building doubly-linked lists, where
 * each node contains a reference to the item preceding it and the item following it in the
 * list ( null if there is no such item). Then implement static methods for the following
 * tasks: insert at the beginning, insert at the end, remove from the beginning, remove
 * from the end, insert before a given node, insert after a given node, and remove a given
 * node.
 *
 ****************************************************************************************************/
public class DoubleLinked<T> {

    public DoubleNode first;

    public class DoubleNode {
        public T item;
        public DoubleNode next;
        public DoubleNode prev;

        @Override
        public String toString() {
            return "DN{item=" + item + ", next=" + next + '}';
        }
    }

    public int size() {
        int cnt = 0;
        DoubleNode x = first;
        while (x != null) {
            cnt++;
            x = x.next;
        }

        return cnt;
    }

    public DoubleLinked<T> addLast(T e) {
        if (first == null) {
            first = new DoubleNode();
            first.item = e;
        } else {
            DoubleNode oldLast = first;
            while (oldLast.next != null)
                oldLast = oldLast.next;
            DoubleNode last = new DoubleNode();
            last.item = e;
            last.prev = oldLast;
            oldLast.next = last;
        }

        return this;
    }

    public DoubleLinked<T> addFirst(T e) {
        if (first == null) {
            first = new DoubleNode();
            first.item = e;
        } else {
            DoubleNode second = first;
            first = new DoubleNode();
            second.prev = first;
            first.next = second;
            first.item = e;
        }

        return this;
    }

    public DoubleLinked<T> removeFirst() {
        DoubleNode oldFirst = first;
        first = first.next;
        oldFirst.next = null;
        return this;
    }

    public DoubleLinked<T> removeLast() {
        DoubleNode oldLast = first;
        while (oldLast.next != null)
            oldLast = oldLast.next;
        DoubleNode newLast = oldLast.prev;
        newLast.next = null;
        oldLast.prev = null;
        return this;
    }

    public boolean contains(T element) {
        DoubleNode tmp = first;
        while (tmp != null) {
            if (tmp.item.equals(element))
                return true;
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public String toString() {
        return first.toString();
    }


    public static void main(String[] args) {
        DoubleLinked<Integer> linked = new DoubleLinked<>();
        linked.addLast(1)
                .addLast(2)
                .addLast(3);

        if (linked.size() != 3)
            throw new RuntimeException();

        System.out.println(linked);
        linked.removeFirst();
        System.out.println("removeFirst: " + linked);

        DoubleLinked<Integer> linked2 = new DoubleLinked<>();
        linked2.addFirst(1)
                .addFirst(2)
                .addFirst(3);

        System.out.println(linked2);
        linked2.removeLast();
        System.out.println("removeLast: " + linked2);
    }
}
