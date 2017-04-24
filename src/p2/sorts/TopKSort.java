package p2.sorts;

import java.util.Comparator;

import datastructures.worklists.MinFourHeap;
import egr221a.exceptions.NotYetImplementedException;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    /*public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);

        for(int i = 0; i < k; i++) {
            if (i >= array.length) {
                i = k;
            } else {
                heap.add(array[i]);
            }
        }

        for(int i = k; i < array.length; i++) {
            if (comparator.compare(array[i], heap.peek()) >= 1) {
                E temp = array[i];
                array[i] = heap.next();
                heap.add(temp);
            }
        }

        int c = Math.min(k, array.length);
        for (int i = c - 1; i >= 0; i--) {
            array[i] = heap.peek();
        }
    }*/

    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        //Queue<E> heap = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            if (i >= array.length) {
                i = k;
            } else {
                heap.add(array[i]);
            }
        }

        for (int i = k; i < array.length; i++) {
            if (comparator.compare(array[i], heap.peek()) >= 1) {
                E temp = array[i];
                array[i] = heap.peek();
                heap.add(temp);
            }
        }

        int c = Math.min(k, array.length);
        // Idk what this is. haha
        /*int c = Math.min(k, array.length);
        for (int i = c - 1; i >= 0; i--) {
            array[i] = heap.peek();
        }*/

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.next();
        }

        // This thing worked, but can we use iterator? Idk...
        /*Iterator itr = heap.iterator();
        int i = 0;
        while (itr.hasNext()) {
            array[i] = (E) itr.next();
            i++;
        }*/
    }
}
