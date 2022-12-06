package _2_Sorting._2_5_Sorting_Applications.creative;

import _2_Sorting._2_3_Quicksort.Quick;

import java.util.Arrays;

/*****************************************************************************************************
 * <p>
 * 2.5.21 Multidimensional sort. Write a Vector data type for use in having the sorting
 * methods sort multidimensional vectors of d integers, putting the vectors in order
 * by first component, those with equal first component in order by second component,
 * those with equal first and second components in order by third component, and so
 * forth.
 *
 ****************************************************************************************************/
public class Vector<T extends Comparable<T>> implements Comparable<Vector<T>> {
    private final T[] array;

    public Vector(T[] array) {
        this.array = array;
    }

    @Override
    public int compareTo(Vector other) {
        //noinspection unchecked
        T[] otherArray = (T[]) other.getArray();
        for (int i = 0; i < array.length; i++) {
            int compare = array[i].compareTo(otherArray[i]);
            if (compare > 0)
                return 1;
            else if (compare < 0)
                return -1;
        }

        return 0;
    }

    public T[] getArray() {
        return array;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) {
        Vector[] vectors = {
                new Vector<>(new Integer[]{1, 5, 4, 3, 2}),
                new Vector<>(new Integer[]{1, 3, 2, 4, 5}),
                new Vector<>(new Integer[]{1, 2, 5, 4, 3}),
                new Vector<>(new Integer[]{1, 4, 3, 2, 5}),
                new Vector<>(new Integer[]{1, 4, 3, 0, 5})
        };

        Quick.sort(vectors);
        for (Vector vector : vectors) {
            System.out.println(vector);
        }
    }
}
