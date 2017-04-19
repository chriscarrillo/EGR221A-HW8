package p2.sorts;

import java.util.Comparator;
import egr221a.exceptions.NotYetImplementedException;

public class HeapSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            percolateDown(array, i, array.length, comparator);
        }
        for (int i = array.length - 1; i < 0; i--) {
            swap(array, 0, i);
            percolateDown(array, 0, i, comparator);
        }
    }

    public static <E> void swap(E[ ] a, int index1, int index2 ) {
        E tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    private static int leftChild( int i ) {
        return 2 * i + 1;
    }

    private static <E> void percolateDown(E[ ] a, int i, int n, Comparator<E> comparator) {
        int child;
        E tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n -1 && comparator.compare(a[child], a[child + 1]) < 0)
                child++;
            if (comparator.compare(tmp, a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }
}
