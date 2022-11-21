package common;

public class TripleNode<Key extends Comparable<Key>> {
    private TripleNode<Key> parent;
    private Key key;
    private TripleNode<Key> left;
    private TripleNode<Key> right;

    public TripleNode(Key key) {
        this.key = key;
    }

    public TripleNode<Key> getParent() {
        return parent;
    }

    public Key getKey() {
        return key;
    }

    public TripleNode<Key> getLeft() {
        return left;
    }

    public TripleNode<Key> getRight() {
        return right;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setParent(TripleNode<Key> parent) {
        this.parent = parent;
    }

    public void setLeft(TripleNode<Key> left) {
        this.left = left;
    }

    public void setRight(TripleNode<Key> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "{`" + key + "`" + ", left=" + left + ", right=" + right + '}';
    }
}
