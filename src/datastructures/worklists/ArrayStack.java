package datastructures.worklists;

import egr221a.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See egr221a/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private E[] array;
    private int index;
    private static final int DEFAULT_SIZE = 10;

    public ArrayStack() {
        clear();
    }

    @Override
    public void add(E work) {
        if (index >= array.length) {
            E[] largeArray = (E[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                largeArray[i] = array[i];
            }
            array = largeArray;
        }
        array[index] = work;
        index++;
    }

    @Override
    public E peek() {
        if (!hasWork()) throw new NoSuchElementException("There is no work.");
        return array[index - 1];
    }

    @Override
    public E next() {
        if (!hasWork()) throw new NoSuchElementException("There is no work.");
        E temp = array[index - 1];
        array[index - 1] = null;
        index--;
        return temp;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public void clear() {
        array = (E[])(new Object[DEFAULT_SIZE]);
        index = 0;
    }
}
