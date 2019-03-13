package _3_Searching._3_5_Searching_Applications.creative;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _3_Searching._3_1_Elementary_Symbol_Tables.SequentialSearchST;

/*****************************************************************************************************
 *
 * 3.5.28 UniQueue. Create a data type that is a queue, except that an element may only
 * be inserted the queue once. Use an existence symbol table to keep track of all elements
 * that have ever been inserted and ignore requests to re-insert such items.
 *
 ****************************************************************************************************/
public class UniQueue<Item extends Comparable<Item>> {

    private static final Object PRESENT = new Object();

    private Queue<Item> queue;
    private SequentialSearchST<Item, Object> st;


    public UniQueue() {
        queue = new Queue<>();
        st = new SequentialSearchST<>();
    }

    public boolean add(Item item) {
        if (st.contains(item))
            return false;

        queue.enqueue(item);
        st.put(item, PRESENT);
        return true;
    }

    public Item pool() {
        Item item = queue.dequeue();
        st.delete(item);
        return item;
    }


    public static void main(String[] args) {
        UniQueue<Integer> queue = new UniQueue<>();

        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));
    }
}
