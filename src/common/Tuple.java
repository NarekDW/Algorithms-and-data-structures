package common;

public class Tuple<Item> {
    private final Item x;
    private final Item y;

    public Tuple(Item x, Item y) {
        this.x = x;
        this.y = y;
    }

    public Item getX() {
        return x;
    }

    public Item getY() {
        return y;
    }
}
