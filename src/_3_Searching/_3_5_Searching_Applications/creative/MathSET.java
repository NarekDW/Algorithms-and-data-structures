package _3_Searching._3_5_Searching_Applications.creative;

import java.util.HashMap;

/*****************************************************************************************************
 * <p>
 * 3.5.17 Mathematical sets. Your goal is to develop an implementation of the following
 * API MathSET for processing (mutable) mathematical sets:
 *
 ****************************************************************************************************/
public class MathSET<Key> {

    private final HashMap<Key, Object> map;
    private static final Object PRESENT = new Object();

    MathSET(Key[] universe) {
        map = new HashMap<>(universe.length);
        for (Key k : universe)
            map.put(k, PRESENT);
    }

    void add(Key key) {
        map.put(key, PRESENT);
    }

    void union(MathSET<Key> a) {
        a.map.keySet().forEach(key -> map.put(key, PRESENT));
    }

    void intersection(MathSET<Key> a) {
        for (Key k : map.keySet())
            if (!a.contains(k))
                delete(k);
    }

    boolean contains(Key key) {
        return map.containsKey(key);
    }

    void delete(Key key) {
        map.remove(key);
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return map.size();
    }
}
