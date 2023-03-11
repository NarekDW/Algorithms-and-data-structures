package _3_Searching._3_5_Searching_Applications.creative;

import _3_Searching._3_5_Searching_Applications.exercises.LinearProbingHashSTWithDuplicates;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 3.5.27 List.
 * Hint : Use two symbol tables, one to find the i th item in the list efficiently, and the other
 * to efficiently search by item. (Java’s java.util.List interface contains methods like
 * these but does not supply any implementation that efficiently supports all
 * operations.)
 *
 ****************************************************************************************************/
public class List<Item extends Comparable<Item>> implements Iterable<Item> {

    private final LinearProbingHashSTWithDuplicates<Item, Integer> items;
    private final LinearProbingHashSTWithDuplicates<Integer, Item> indexes;
    private int n;

    public List() {
        items = new LinearProbingHashSTWithDuplicates<>();
        indexes = new LinearProbingHashSTWithDuplicates<>();
    }

    void addFront(Item item) {
        items.put(item, 0);
        indexes.put(0, item);
        n++;
    }

    void addBack(Item item) {
        items.put(item, n);
        indexes.put(n, item);
        n++;
    }

    Item deleteFront() {
        Item item = indexes.get(0);
        indexes.deleteOne(0);
        items.deleteOne(item);
        n--;
        return item;
    }

    Item deleteBack() {
        Item item = indexes.get(n);
        if (item != null) {
            indexes.deleteOne(n);
            items.deleteOne(item);
            n--;
        }

        return item;
    }

    void delete(Item item) {
        indexes.deleteOne(items.get(item));
        items.deleteOne(item);
        n--;
    }

    void add(int i, Item item) {
        items.put(item, i);
        indexes.put(i, item);
        n++;
    }

    Item delete(int i) {
        Item item = indexes.get(i);
        items.deleteOne(item);
        indexes.deleteOne(i);
        return item;
    }

    boolean contains(Item item) {
        return items.contains(item);
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return items.keys().iterator();
    }


    public static void main(String[] args) {
        List<String> list = new List<>();

        list.addFront("a");
        list.addFront("b");
        list.addFront("c");
        list.addFront("d");

        list.forEach(System.out::println);

        list.deleteFront();
        list.deleteFront();

        System.out.println();
        list.forEach(System.out::println);

        list.addBack("e");
        list.addBack("f");

        list.add(1, "x");
        list.add(2, "y");

        System.out.println();
        list.forEach(System.out::println);

        list.deleteFront();

        System.out.println();
        list.forEach(System.out::println);

        list.delete(1);

        System.out.println();
        list.forEach(System.out::println);

        list.delete("y");

        System.out.println();
        list.forEach(System.out::println);
    }
}
