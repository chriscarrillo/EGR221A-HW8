package datastructures.worklists;

import egr221a.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {
    private E[] arr;
    private int size;
    private int front;
    private int end;

    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        clear();
    }

    /**
     * Adds work to the worklist.  This method should conform to any additional contracts that
     * the particular type of worklist has.
     *
     * @param work the work to add to the worklist
     */
    @Override
    public void add(E work) {
        if(isFull()) {
            throw new IllegalStateException();
        }
        arr[end] = work;
        end = (end + 1) % capacity();
        size++;
    }

    /**
     * Returns a view to the next element of the worklist.
     *
     * @precondition hasWork() is true
     * @postcondition return(peek()) is return(next())
     * @postcondition the structure of this worklist remains unchanged.
     * @throws NoSuchElementException if hasWork() is false
     * @return the next element in this worklist
     */
    @Override
    public E peek() {
        return peek(0);
    }

    /**
     * Returns a view of the ith element of the worklist.  Since this worklist is a FIFO
     * worklist, it has a well-defined order.
     *
     * @precondition 0 <= i < size()
     * @postcondition the structure of this worklist remains unchanged
     * @throws NoSuchElementException if hasWork() is false
     *         (this exception takes precedence over all others)
     * @throws IndexOutOfBoundsException if i < 0 or i >= size()
     *
     * @param i    the index of the element to peek at
     * @return the ith element in this worklist
     */
    @Override
    public E peek(int i) {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        if(i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return arr[(front + i) % capacity()];
    }

    /**
     * Returns and removes the next element of the worklist
     *
     * @precondition hasWork() is true
     * @postcondition return(next()) + after(next()) == before(next())
     * @postcondition after(size()) + 1 == before(size())
     * @throws NoSuchElementException if hasWork() is false
     * @return the next element in this worklist
     */
    @Override
    public E next() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        E temp = arr[front];
        arr[front] = null;
        size--;
        front = (front + 1) % capacity();
        return temp;
    }

    /**
     * Replaces the ith element of this worklist with value.  Since this worklist
     * is a FIFO worklist it has a well-defined order.
     *
     * @precondition 0 <= i < size()
     * @postcondition only the ith element of the structure is changed
     * @throws NoSuchElementException if hasWork() is false
     *         (this exception takes precedence over all others)
     * @throws IndexOutOfBoundsException if i < 0 or i >= size()
     *
     * @param i    the index of the element to update
     * @param value the value to update index i with
     */
    @Override
    public void update(int i, E value) {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        if(i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        arr[(front + i) % capacity()] = value;
    }

    /**
     * Returns the number of elements of work remaining in this worklist
     *
     * @return the size of this worklist
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Resets this worklist to the same state it was in right after construction.
     */
    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        E[] newArr = (E[])new Comparable[super.capacity()];
        arr = newArr;
        size = 0;
        front = 0;
        end = 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CircularArrayFIFOQueue<?>)) {
            return false;
        }
        CircularArrayFIFOQueue<E> newOther = (CircularArrayFIFOQueue<E>)other;
        if (size() != newOther.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if(!peek(i).equals(newOther.peek(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int code = 17;
        int i = 0;
        for (E e : this) {
            code += e.hashCode() * Math.pow(31, i);
            i++;
        }
        return code;
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        int size1 = size();
        int size2 = other.size();
        int bound = Math.min(size1, size2);
        for (int i = 0; i < bound; i++) {
            int equals = peek(i).compareTo(other.peek(i));
            if (equals != 0) {
                return equals;
            }
        }
        if (size1 == size2) {
            return 0;
        } else if (size1 > size2) {
            return 1;
        } else {
            return -1;
        }
    }
}