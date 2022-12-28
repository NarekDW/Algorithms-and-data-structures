package common;

public class Tuple2<K1, K2> {
    private final K1 x;
    private final K2 y;

    public Tuple2(K1 x, K2 y) {
        this.x = x;
        this.y = y;
    }

    public K1 getX() {
        return x;
    }

    public K2 getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + '}';
    }
}
