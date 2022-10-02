package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

import common.StdRandom;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 1.3.34 Random bag. A random bag stores a collection of items and supports the following API:
 *       public class RandomBag<Item> implements Iterable<Item>
 *          RandomBag()
 *          boolean isEmpty()
 *          int size()
 *          void add(Item item)
 * <p>
 * Write a class RandomBag that implements this API.
 * Note that this API is the same as for Bag, except for the adjective random, which indicates
 * that the iteration should provide the items in random order (all N! permutations equally likely, for each iterator).
 * Hint : Put the items in an array and randomize their order in the iterator’s constructor.
 *
 ****************************************************************************************************/
public class RandomBag<Item> implements Iterable<Item> {
    private Item[] items;
    private int n;

    public RandomBag() {
        //noinspection unchecked
        this.items = (Item[]) new Object[2];
        this.n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        if (n == items.length)
            resize(items.length * 2);
        items[n++] = item;
    }

    private void resize(int max) {
        //noinspection unchecked
        Item[] tmp = (Item[]) new Object[max];
        //noinspection ManualArrayCopy
        for (int i = 0; i < n; i++)
            tmp[i] = items[i];
        items = tmp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        int rn = 0;

        public RandomIterator() {
            StdRandom.shuffle(items, 0, n);
        }

        @Override
        public boolean hasNext() {
            return rn < n;
        }

        @Override
        public Item next() {
            return items[rn++];
        }
    }


    public static void main(String[] args) {
        RandomBag<Integer> randomBag = new RandomBag<>();
        for (int i = 0; i < 5; i++)
            randomBag.add(i);

        for (Integer i : randomBag)
            System.out.print(i + " ");
    }
}
