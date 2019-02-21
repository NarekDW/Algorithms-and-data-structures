package _3_Searching;

@SuppressWarnings("Duplicates")
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int n;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
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
        if (i < n && key.compareTo(keys[i]) == 0)
            return values[i];
        else
            return null;
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            values[i] = value;
            return;
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        n++;
    }

    public int rank(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    // Recursive solution
    public int rank(Key key, int lo, int hi) {
        if (lo > hi) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp > 0)
            return rank(key, mid + 1, hi);
        else if (cmp < 0)
            return rank(key, lo, mid - 1);
        else
            return mid;
    }

    /*****************************************************************************************************
     * 3.1.16 Implement the delete() method for BinarySearchST .
     ****************************************************************************************************/
    public void delete(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            for (int j = i; j < n - 1; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            keys[n - 1] = null;
            values[n - 1] = null;
            n--;
        }
    }


    /*****************************************************************************************************
     * 3.1.17 Implement the floor() method for BinarySearchST .
     ****************************************************************************************************/
    public Key floor(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return keys[i];
        if (i == 0) return null;
        return keys[i - 1];
    }

    private void show() {
        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + " " + values[i]);
        }
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, String> bs = new BinarySearchST<>(3);
        bs.put(1, "a");
        bs.put(2, "b");
        bs.put(3, "c");

        bs.delete(2);
        bs.show();
    }
}
