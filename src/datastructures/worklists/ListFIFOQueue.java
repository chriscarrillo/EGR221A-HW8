package datastructures.worklists;

import egr221a.interfaces.worklists.FIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See egr221a/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private QueueNode<E> front;
    private QueueNode<E> current;
    private int size;

    public ListFIFOQueue() {
        clear();
    }

    @Override
    public void add(E work) {
        if (front == null) {
            front = new QueueNode(work);
            current = front;
        } else {
            current.next = new QueueNode(work);
            current = current.next;
        }
        size++;
    }

    @Override
    public E peek() {
        if (!hasWork()) throw new NoSuchElementException("There is no work.");
        return front.work;
    }

    @Override
    public E next() {
        if (!hasWork()) throw new NoSuchElementException("There is no work.");
        QueueNode<E> temp = front;
        front = front.next;
        size--;
        return temp.work;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        current = front;
        size = 0;
    }

    /**
     * QueueNode<E> is the node for ListFIFOQueue
     * @param <E>
     */
    private static class QueueNode<E> {
        public E work;
        public QueueNode<E> next;

        public QueueNode(E work) {
            this.work = work;
        }
    }
}
