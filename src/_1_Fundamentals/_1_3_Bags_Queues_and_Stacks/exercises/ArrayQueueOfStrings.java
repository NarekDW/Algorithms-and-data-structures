package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

public class ArrayQueueOfStrings<Item> {

    private Item[] items;
    private int first;
    private int last;

    public ArrayQueueOfStrings(int cap) {
        items = (Item[]) new Object[cap];
        last = first = 0;
    }

    public void enqueue(Item item) {
        items[last] = item;
        incrementLast();
    }

    public Item dequeue() {
        Item item = items[first];
        items[first] = null;
        incrementFirst();
        return item;
    }

    public boolean isEmpty() {
        return first - last == 0;
    }

    public int size() {
        return items.length;
    }

    private void incrementLast() {
        last++;
        if (last >= items.length)
            last = 0;
    }

    private void incrementFirst() {
        first++;
        if (first >= items.length)
            first = 0;
    }

    public static void main(String[] args) {
        ArrayQueueOfStrings<Integer> queue = new ArrayQueueOfStrings<>(5);
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
    }
}
