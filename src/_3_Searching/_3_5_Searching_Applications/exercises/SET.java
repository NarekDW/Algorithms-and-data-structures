package _3_Searching._3_5_Searching_Applications.exercises;

import common.StdOut;

import java.util.Iterator;
import java.util.TreeSet;

/*****************************************************************************************************
 *
 * 3.5.1 Implement SET and HashSET as “wrapper class” clients of ST and HashST , respec-
 * tively (provide dummy values and ignore them).
 *
 ****************************************************************************************************/
public class SET<Key extends Comparable<Key>> implements Iterable<Key> {

    private TreeSet<Key> set;

    public SET() {
        set = new TreeSet<>();
    }

    public SET(SET<Key> x) {
        set = new TreeSet<>(x.set);
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
        return set.last();
    }

    public Key min() {
        return set.first();
    }

    public Key ceiling(Key key) {
        return set.ceiling(key);
    }

    public Key floor(Key key) {
        return set.floor(key);
    }

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


    /**
     * Compares this set to the specified set.
     * <p>
     * Note that this method declares two empty sets to be equal
     * even if they are parameterized by different generic types.
     * This is consistent with the behavior of {@code equals()}
     * within Java's Collections framework.
     *
     * @param  other the other set
     * @return {@code true} if this set equals {@code other};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        SET that = (SET) other;
        return this.set.equals(that.set);
    }

    /**
     * This operation is not supported because sets are mutable.
     *
     * @return does not return a value
     * @throws UnsupportedOperationException if called
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
    }

    /**
     * Returns a string representation of this set.
     *
     * @return a string representation of this set, enclosed in curly braces,
     *         with adjacent keys separated by a comma and a space
     */
    @Override
    public String toString() {
        String s = set.toString();
        return "{ " + s.substring(1, s.length() - 1) + " }";
    }

    /**
     * Unit tests the {@code SET} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SET<String> set = new SET<>();
        StdOut.println("set = " + set);

        // insert some keys
        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");    // overwrite old value
        set.add("www.princeton.edu");
        set.add("www.math.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");
        set.add("www.stanford.edu");
        set.add("www.google.com");
        set.add("www.ibm.com");
        set.add("www.apple.com");
        set.add("www.slashdot.com");
        set.add("www.whitehouse.gov");
        set.add("www.espn.com");
        set.add("www.snopes.com");
        set.add("www.movies.com");
        set.add("www.cnn.com");
        set.add("www.iitb.ac.in");


        StdOut.println(set.contains("www.cs.princeton.edu"));
        StdOut.println(!set.contains("www.harvardsucks.com"));
        StdOut.println(set.contains("www.simpsons.com"));
        StdOut.println();

        StdOut.println("ceiling(www.simpsonr.com) = " + set.ceiling("www.simpsonr.com"));
        StdOut.println("ceiling(www.simpsons.com) = " + set.ceiling("www.simpsons.com"));
        StdOut.println("ceiling(www.simpsont.com) = " + set.ceiling("www.simpsont.com"));
        StdOut.println("floor(www.simpsonr.com)   = " + set.floor("www.simpsonr.com"));
        StdOut.println("floor(www.simpsons.com)   = " + set.floor("www.simpsons.com"));
        StdOut.println("floor(www.simpsont.com)   = " + set.floor("www.simpsont.com"));
        StdOut.println();

        StdOut.println("set = " + set);
        StdOut.println();

        // print out all keys in this set in lexicographic order
        for (String s : set) {
            StdOut.println(s);
        }

        StdOut.println();
        SET<String> set2 = new SET<>(set);
        StdOut.println(set.equals(set2));
    }

}
