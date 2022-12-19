package common;

public class SimpleNode<Key extends Comparable<Key>, Value> {
    public Key key;
    public Value value;
    public SimpleNode<Key, Value> next;

    public SimpleNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SimpleNode{key=" + key + ", value=" + value + ", next=" + next + '}';
    }
}
