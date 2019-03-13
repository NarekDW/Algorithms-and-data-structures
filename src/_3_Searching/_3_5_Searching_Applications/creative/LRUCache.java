package _3_Searching._3_5_Searching_Applications.creative;

/*****************************************************************************************************
 *
 * 3.5.26 LRU cache. Create a data structure that supports the following operations: ac-
 * cess and remove. The access operation inserts the item onto the data structure if it’s
 * not already present. The remove operation deletes and returns the item that was least
 * recently accessed. Hint : Maintain the items in order of access in a doubly linked list,
 * along with pointers to the first and last nodes. Use a symbol table with keys = items,
 * values = location in linked list. When you access an element, delete it from the linked
 * list and reinsert it at the beginning. When you remove an element, delete it from the end
 * and remove it from the symbol table.
 *
 ****************************************************************************************************/
public class LRUCache<Item> {

    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node prev;
        Node next;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    public Item apply(Item item) {
        for (Node x = last; x != null; x = x.prev)
            if (x.item.equals(item)) {
                Node prev = x.prev;
                if (x.prev != null)
                    x.prev.next = x.next;
                if (x.next != null) {
                    x.next.prev = prev;
                    first = x.next;
                }
            }

        Node node = new Node(item, last, null);
        if (last != null)
            last.next = node;
        last = node;

        if (first == null)
            first = last;

        return this.last.item;
    }

    public Item delete() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public String toString() {
        return first.toString();
    }

    public static void main(String[] args) {
        LRUCache<Integer> cache = new LRUCache<>();

        cache.apply(1);
        cache.apply(2);
        cache.apply(3);
        cache.apply(1);

        cache.delete();
        System.out.println(cache);
    }
}
