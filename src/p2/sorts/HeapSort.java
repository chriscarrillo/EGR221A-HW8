package p2.sorts;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import datastructures.worklists.MinFourHeap;
import egr221a.datastructures.containers.Item;
import egr221a.exceptions.NotYetImplementedException;

public class HeapSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        for (int i = 0; i < array.length; i++) {
            heap.add(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.next();
        }

    }

    /*public static <E> void sort(E[] array, Comparator<E> comparator) {
        Queue<E> queue = new PriorityQueue<>();

        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }

        for (int i = 0 ; i < array.length; i++) {
            array[i] = queue.remove();
        }
    }*/
}
