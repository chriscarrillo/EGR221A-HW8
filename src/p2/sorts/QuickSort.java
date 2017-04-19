package p2.sorts;

import java.util.Comparator;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import egr221a.exceptions.NotYetImplementedException;
import egr221a.sorts.InsertionSort;

public class QuickSort {

    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        sort(array, 0, array.length - 1, comparator);
    }

    private static <E> void sort(E[] a, int left, int right, Comparator<E> comparator) {
        if (left <= right) {
            E pivot = median3(a, left, right, comparator);

            // Begin partitioning
            int i = left, j = right - 1;
            for ( ; ; ) {
                while (comparator.compare(a[++i], pivot) < 0) { }
                while (comparator.compare(a[--j], pivot) > 0) { }
                if (i < j)
                    swap(a, i, j);
                else
                    break;
            }

            swap(a, i, right - 1); // Restore pivot

            QuickSort.sort(a, left, i - 1, comparator);
            QuickSort.sort(a, i + 1, right, comparator);
        }
    }

    private static <E> void swap(E[ ] a, int index1, int index2 ) {
        E tmp = a[index1];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    private static <E> E median3(E[ ] a, int left, int right, Comparator<E> comparator) {
        int center = (left + right) / 2;
        /*if (comparator.compare(a[left], a[right]) < 0 && comparator.compare(a[right], a[center]) < 0
                || comparator.compare(a[center], a[right]) < 0 && comparator.compare(a[right], a[left]) < 0)
            swap(a, left, right);
        else if (comparator.compare(a[right], a[left]) < 0 && comparator.compare(a[left], a[center]) < 0 ||
                comparator.compare(a[center], a[left]) < 0 && comparator.compare(a[left], a[right]) < 0)
            swap(a, left, center);*/
        if (comparator.compare(a[center], a[left]) < 0)
            swap(a, left, center);
        if (comparator.compare(a[right], a[left]) < 0)
            swap(a, left, right);
        if (comparator.compare(a[right], a[center]) < 0)
            swap(a, center, right);

        swap(a, center, right - 1);
        return a[right - 1];
    }
}