package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.creative;

/*****************************************************************************************************
 * <p>
 * 1.3.39 Ring buffer.
 * A ring buffer, or circular queue, is a FIFO data structure of a fixed size N.
 * It is useful for transferring data between asynchronous processes or for storing log files.
 * When the buffer is empty, the consumer waits until data is deposited; when the buffer is full,
 * the producer waits to deposit data. Develop an API for a RingBuffer and an implementation that
 * uses an array representation (with circular wrap-around).
 *
 ****************************************************************************************************/
public class RingBuffer<Item> {

    private final Item[] items;
    private int n;
    private final CircularPointer left;
    private final CircularPointer right;


    public RingBuffer(int bufferSize) {
        //noinspection unchecked
        this.items = (Item[]) new Object[bufferSize];
        this.left = new CircularPointer(0, bufferSize);
        this.right = new CircularPointer(0, bufferSize);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == items.length;
    }

    public boolean deposit(Item item) {
        if (isFull())
            return false;
        items[right.getIncrement()] = item;
        n++;
        return true;
    }

    public Item unDeposit() {
        if (isEmpty())
            return null;
        Item item = items[left.getIncrement()];
        n--;
        return item;
    }

    private static class CircularPointer {
        private int point;
        private final int max;

        public CircularPointer(int point, int max) {
            this.point = point;
            this.max = max;
        }

        public int getIncrement() {
            if (point == max)
                point = 0;
            int tmp = point;
            point++;
            return tmp;
        }
    }


    public static void main(String[] args) {
        System.out.println("Running test for RingBuffer.");
        int n = 10;
        RingBuffer<Integer> buffer = generateBuffer();
        if (buffer.isEmpty())
            throw new RuntimeException("isEmpty");
        if (!buffer.isFull())
            throw new RuntimeException("!isFull");

        for (int i = 0; i < n; i++)
            if (buffer.unDeposit() != i)
                throw new RuntimeException(String.valueOf(i));

        if (buffer.unDeposit() != null)
            throw new RuntimeException("unDeposit != null");
        if (!buffer.isEmpty())
            throw new RuntimeException("!isEmpty");
        if (buffer.isFull())
            throw new RuntimeException("isFull");

        buffer = generateBuffer();
        for (int i = 0; i < 3; i++)
            buffer.unDeposit();

        if (!buffer.deposit(11))
            throw new RuntimeException("11");
        if (!buffer.deposit(22))
            throw new RuntimeException("22");
        if (!buffer.deposit(33))
            throw new RuntimeException("33");
        if (buffer.deposit(44))
            throw new RuntimeException("44");

        if (buffer.unDeposit() != 3)
            throw new RuntimeException("unDeposit() != 3");
    }

    private static RingBuffer<Integer> generateBuffer() {
        RingBuffer<Integer> buffer = new RingBuffer<>(10);
        for (int i = 0; i < 10; i++)
            buffer.deposit(i);
        return buffer;
    }
}
