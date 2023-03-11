package _3_Searching._3_5_Searching_Applications.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _3_Searching._3_4_Hash_Tables.SeparateChainingHashST;

import java.util.Iterator;

/*****************************************************************************************************
 * <p>
 * 3.5.11 Develop a MultiSET class that is like SET,but allows equal keys and thus implements
 * a mathematical multiset.
 *
 ****************************************************************************************************/
public class MultiSet<Key extends Comparable<Key>> {
    private final SeparateChainingHashST<Key, Integer> st;
    private int size;

    public MultiSet() {
        this.st = new SeparateChainingHashST<>();
    }

    public void add(Key key) {
        if (st.contains(key)) {
            st.put(key, st.get(key) + 1);
        } else {
            st.put(key, 1);
        }
        size++;
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public void delete(Key key) {
        Integer appearance = st.get(key);
        if (appearance > 1) {
            st.put(key, appearance - 1);
        } else {
            st.delete(key);
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<Key> iterator() {
        Queue<Key> queue = new Queue<>();
        for (Key key : st.keys()) {
            for (int i = 0; i < st.get(key); i++) {
                queue.enqueue(key);
            }
        }
        return queue.iterator();
    }

    public static void main(String[] args) {
        MultiSet<Integer> multiSet = new MultiSet<>();
        multiSet.add(1);
        multiSet.add(1);
        multiSet.add(1);
        multiSet.add(2);
        multiSet.add(2);
        multiSet.add(3);

        System.out.println(multiSet.size);
        System.out.println(multiSet.st.get(1));
        System.out.println(multiSet.st.get(2));
        System.out.println(multiSet.st.get(3));

        multiSet.delete(1);
        multiSet.delete(1);
        System.out.println(multiSet.contains(1));
        multiSet.delete(1);
        System.out.println(multiSet.contains(1));
    }
}
