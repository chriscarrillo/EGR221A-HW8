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

    private static <E> int getMedian(E[] arr, int alpha, int beta, int charlie, Comparator<E> comparator) {
        if (verifyMedian(arr[alpha], arr[beta], arr[charlie], comparator)) {
            return beta;
        } else if (verifyMedian(arr[beta], arr[alpha], arr[charlie], comparator)) {
            return alpha;
        } else {
            return charlie;
        }
    }

    private static <E> boolean verifyMedian(E alpha, E beta, E charlie, Comparator<E> comparator) {
        return (comparator.compare(alpha, beta) < 0 && comparator.compare(beta, charlie) < 0) ||
                (comparator.compare(charlie, beta) < 0 && comparator.compare(beta, alpha) < 0);
    }

    private static <E> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}