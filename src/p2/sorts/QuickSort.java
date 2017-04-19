package p2.sorts;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        int left = 0;
        int right = array.length - 1;
        sort(array, left, right, comparator);
    }

    private static <E> void sort(E[] array, int left, int right, Comparator<E> comparator) {
        if (left < right) {
            int i = getMedian(array, left, right - 1, left + (right - 1 - left) / 2, comparator);
            int front = left + 1;
            int end = right;

            swap(array, left, i);
            E pivot = array[left];

            i = left;
            while (front <= end) {
                if (comparator.compare(array[front], pivot) >= 0) {
                    swap(array, front, end);
                    end--;
                } else {
                    swap(array, front, i);
                    front++;
                    i++;
                }
            }
            sort(array, left, i, comparator);
            sort(array, i + 1, right, comparator);
        }
    }

    private static <E> int getMedian(E[] arr, int a, int b, int c, Comparator<E> comparator) {
        if (verifyMedian(arr[a], arr[b], arr[c], comparator)) {
            return b;
        } else if (verifyMedian(arr[b], arr[a], arr[c], comparator)) {
            return a;
        } else {
            return c;
        }
    }

    private static <E> boolean verifyMedian(E a, E b, E c, Comparator<E> comparator) {
        return (comparator.compare(a, b) < 0 && comparator.compare(b, c) < 0) ||
                (comparator.compare(c, b) < 0 && comparator.compare(b, a) < 0);
    }

    private static <E> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}