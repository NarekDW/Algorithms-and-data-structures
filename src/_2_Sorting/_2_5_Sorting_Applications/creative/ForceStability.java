package _2_Sorting._2_5_Sorting_Applications.creative;

import java.util.function.Function;


/*****************************************************************************************************
 * <p>
 * 2.5.18 Force stability. Write a wrapper method that makes any sort stable by
 * creating a new key type that allows you to append each key’s index to the key,
 * call sort(), then restore the original key after the sort.
 *
 ****************************************************************************************************/
public class ForceStability {

    public static <T extends Comparable<T>> void wrapper(T[] arr, Function<Comparable[], Void> sortFunction) {
        int n = arr.length;
        StableSortEntity<T>[] stableClone = new StableSortEntity[n];
        for (int i = 0; i < n; i++) {
            stableClone[i] = new StableSortEntity<>(arr[i], i);
        }

        sortFunction.apply(stableClone);

        for (int i = 0; i < n; i++) {
            arr[i] = stableClone[i].entity;
        }
    }

    public static class StableSortEntity<T extends Comparable<T>> implements Comparable<StableSortEntity<T>> {
        T entity;
        int index;

        public StableSortEntity(T entity, int index) {
            this.entity = entity;
            this.index = index;
        }

        @Override
        public int compareTo(StableSortEntity<T> o) {
            int result = entity.compareTo(o.entity);
            if (result == 0)
                return o.index - index;
            return result;
        }
    }

}
