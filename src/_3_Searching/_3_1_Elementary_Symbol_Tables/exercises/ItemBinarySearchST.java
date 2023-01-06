package _3_Searching._3_1_Elementary_Symbol_Tables.exercises;

import _2_Sorting._2_2_Mergesort.Merge;
import _3_Searching.SymbolTable;
import _3_Searching.SymbolTableTest;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 3.1.12 Modify BinarySearchST to maintain one array of Item objects that contain
 * keys and values, rather than two parallel arrays. Add a constructor that takes an array of
 * Item values as argument and uses mergesort to sort the array.
 *
 ****************************************************************************************************/
public class ItemBinarySearchST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private final Item[] items;
    private int n;

    public ItemBinarySearchST(int capacity) {
        //noinspection unchecked
        items = new ItemBinarySearchST.Item[capacity];
    }

    public ItemBinarySearchST(Item[] items) {
        Merge.sort(items);
        this.items = items;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterable<Key> keys() {
        return () -> new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            public Key next() {
                return items[i++].key;
            }
        };
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

    @Override
    public void delete(Key key) {
        int i = rank(key);
        if (i < n && items[i].compareTo(key) == 0) {
            for (int j = i; j < n - 1; j++) {
                items[j] = items[j + 1];
            }
            items[n - 1] = null;
            n--;
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
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
        ItemBinarySearchST<Integer, String> st = new ItemBinarySearchST<>(10);
        SymbolTableTest.testST(st);

        System.out.println(st.get(1));
        st.put(1, "a");
        st.put(2, "b");
        st.put(3, "c");

        System.out.println(st.get(1));
        System.out.println(st.get(2));
        System.out.println(st.get(3));

        st.put(2, "z");
        System.out.println(st.get(2));
    }
}
