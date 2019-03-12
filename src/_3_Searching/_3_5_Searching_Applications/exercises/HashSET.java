package _3_Searching._3_5_Searching_Applications.exercises;

import java.util.HashSet;
import java.util.Iterator;

/*****************************************************************************************************
 *
 * 3.5.1 Implement SET and HashSET as “wrapper class” clients of ST and HashST , respec-
 * tively (provide dummy values and ignore them).
 *
 ****************************************************************************************************/
public class HashSET<Key extends Comparable<Key>> implements Iterable<Key> {

    private HashSet<Key> set;

    public HashSET() {
        set = new HashSet<>();
    }

    public HashSET(HashSet<Key> that) {
        set = new HashSet<>(that);
    }

    public void add(Key key) {
        set.add(key);
    }

    public boolean contains(Key key) {
        return set.contains(key);
    }

    public void delete(Key key) {
        set.remove(key);
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    public Key max() {
        Iterator<Key> it = set.iterator();
        Key max = null;
        if (it.hasNext())
            max = it.next();
        while (it.hasNext()) {
            Key x = it.next();
            if (x.compareTo(max) > 0)
                max = x;
        }

        return max;
    }

//    public Key min() {}

//    public Key ceiling(Key key) {
//        return set.ceiling(key);
//    }
//
//    public Key floor(Key key) {
//        return set.floor(key);
//    }

    public SET<Key> union(SET<Key> that) {
        SET<Key> x = new SET<>();
        for (Key k : this)
            x.add(k);
        for (Key k : that)
            x.add(k);
        return x;
    }

    public SET<Key> intersects(SET<Key> that) {
        SET<Key> x = new SET<>();
        if (this.size() < that.size()) {
            for (Key k : this)
                if (that.contains(k))
                    x.add(k);
        } else {
            for (Key k : that)
                if (this.contains(k))
                    x.add(k);

        }

        return x;
    }
}
