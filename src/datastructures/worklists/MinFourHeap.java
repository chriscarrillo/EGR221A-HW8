package datastructures.worklists;

import egr221a.interfaces.worklists.PriorityWorkList;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * See egr221a/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private static final int DEFAULT_CAPACITY = 21;
    private static final int CHILDREN = 4;
    private int size;
    private int level;
    private Comparator<E> comparator;


    public MinFourHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        clear();
    }

    @Override
    public boolean hasWork() {
        return size != 0;
    }

    @Override
    public void add(E work) {
        if(size == data.length) {
            level++;
            E[] largerHeap = (E[]) new Object[(int) (data.length + Math.pow(CHILDREN, level))];
            for(int i = 0; i < data.length; i++) {
                largerHeap[i] = data[i];
            }
            data = largerHeap;
        }
        data[size] = (E) work;

        int i = size;
        while(i != 0 && comparator.compare(data[i], data[(i - 1) / CHILDREN]) < 0) {
            swap(data, i, (i - 1) / CHILDREN);
            i = (i - 1) / CHILDREN;
        }
        size++;
    }

    @Override
    public E peek() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        return data[0];
    }

    @Override
    public E next() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        E temp = data[0];
        data[0] = null;
        swap(data, 0, size - 1);
        size--;

        int i = 0;
        int indexOfSmallestOfFour = findMinOfFour(data, i);

        while (indexOfSmallestOfFour != -1 &&
                comparator.compare(data[i], data[indexOfSmallestOfFour]) > 0) {
            swap(data, i, indexOfSmallestOfFour);

            i = indexOfSmallestOfFour;
            indexOfSmallestOfFour = findMinOfFour(data, i);
        }

        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

        E[] newData = (E[])new Object[DEFAULT_CAPACITY];
        data = newData;
        size = 0;
        level = 2;
    }

    private void swap(E[] data, int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private int findMinOfFour(E[] data, int i) {
        if(CHILDREN * i + 4 >= size) {
            if(CHILDREN * i + 1 >= size) {
                return -1;
            } else if (CHILDREN * i + 2 >= size) {
                return CHILDREN * i + 1;
            } else if (CHILDREN * i + 3 >= size) {
                int smaller =
                        comparator.compare(data[CHILDREN * i + 1], data[CHILDREN * i + 2]) <= 0
                                ? (CHILDREN * i + 1) : (CHILDREN * i + 2);
                return smaller;
            } else {
                int smaller =
                        comparator.compare(data[CHILDREN * i + 1], data[CHILDREN * i + 2]) <= 0
                                ? (CHILDREN * i + 1) : (CHILDREN * i + 2);
                int smallest =
                        comparator.compare(data[smaller], data[CHILDREN * i + 3]) <= 0
                                ? smaller : (CHILDREN * i + 3);
                return smallest;
            }
        } else {
            int indexOfSmallerOfLeftTwo =
                    comparator.compare(data[CHILDREN * i + 1], data[CHILDREN * i + 2]) <= 0
                            ? (CHILDREN * i + 1) : (CHILDREN * i + 2);
            int indexOfSmallerOfRightTwo =
                    comparator.compare(data[CHILDREN * i + 3], data[CHILDREN * i + 4]) <= 0
                            ? (CHILDREN * i + 3) : (CHILDREN * i + 4);
            int indexOfSmallestOfFour =
                    comparator.compare(data[indexOfSmallerOfLeftTwo], data[indexOfSmallerOfRightTwo]) <= 0
                            ? indexOfSmallerOfLeftTwo : indexOfSmallerOfRightTwo;
            return indexOfSmallestOfFour;
        }
    }
}
