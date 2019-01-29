package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

/*****************************************************************************************************
 *
 * 1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue
 * abstraction with a fixed-size array, and then extend your implementation to use array
 * resizing to remove the size restriction.
 *
 ****************************************************************************************************/
public class ResizingArrayQueueOfStrings<Item> {

    private Item[] items;
    private int first;
    private int last;

    public ResizingArrayQueueOfStrings(int cap) {
        items = (Item[]) new Object[cap];
        last = first = 0;
    }

    public void enqueue(Item item) {
        if (last == items.length)
            resize(items.length * 2);
        items[last++] = item;
    }

    public Item dequeue() {
        Item item = items[first];
        items[first] = null;
        first++;
        return item;
    }

    public boolean isEmpty() {
        return first - last == 0;
    }

    public int size() {
        return items.length;
    }

    private void resize(int max) {
        if (first >= max / 4) {
            for (int i = 0; i < last - first; i++) {
                items[i] = items[i + first];
                items[i + first] = null;
            }
            last = last - first;
            first = 0;
        } else {
            Item[] tmp = (Item[]) new Object[max];
            for (int i = 0; i < items.length; i++) {
                tmp[i] = items[i];
            }
            items = tmp;
        }
    }


    // TEST
    public static void main(String[] args) {
        ResizingArrayQueueOfStrings<Integer> queue = new ResizingArrayQueueOfStrings<>(2);
        System.out.println("isEmpty: " + queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue.dequeue());

        queue.enqueue(6);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println(queue.dequeue());
        System.out.println("isEmpty: " + queue.isEmpty());


        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);

        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(18);
        queue.enqueue(19);
        queue.enqueue(20);
        queue.enqueue(21);
        queue.enqueue(22);
        queue.enqueue(23);
    }
}
