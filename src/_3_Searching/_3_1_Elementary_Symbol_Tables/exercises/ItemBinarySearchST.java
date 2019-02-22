package _3_Searching._3_1_Elementary_Symbol_Tables.exercises;

import _2_Sorting._2_2_Mergesort.Merge;

/*****************************************************************************************************
 *
 * 3.1.12 Modify BinarySearchST to maintain one array of Item objects that contain
 * keys and values, rather than two parallel arrays. Add a constructor that takes an array of
 * Item values as argument and uses mergesort to sort the array.
 *
 ****************************************************************************************************/
@SuppressWarnings("Duplicates")
public class ItemBinarySearchST<Key extends Comparable<Key>, Value> {

    private Item[] items;
    private int n;

    public ItemBinarySearchST(int capacity) {
        items = new ItemBinarySearchST.Item[capacity];
    }

    public ItemBinarySearchST(Item[] items) {
        Merge.sort(items);
        this.items = items;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && items[i].compareTo(key) == 0)
            return items[i].value;
        else
            return null;
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < n && items[i].compareTo(key) == 0) {
            items[i].value = value;
            return;
        }

        for (int j = n; j > i; j--)
            items[j] = items[j - 1];
        items[i] = new Item(key, value);
        n++;
    }

    private class Item implements Comparable<Key> {
        Key key;
        Value value;

        public Item(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Key o) {
            return key.compareTo(o);
        }
    }

    private int rank(Key key) {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(items[mid].key);

            if (cmp > 0) lo = mid + 1;
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }

        return lo;
    }


    public static void main(String[] args) {
        ItemBinarySearchST<Integer, String> x = new ItemBinarySearchST<>(3);
        System.out.println(x.get(1));
        x.put(1, "a");
        x.put(2, "b");
        x.put(3, "c");

        System.out.println(x.get(1));
        System.out.println(x.get(2));
        System.out.println(x.get(3));

        x.put(2, "z");
        System.out.println(x.get(2));
    }
}
