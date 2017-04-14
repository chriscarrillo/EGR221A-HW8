package datastructures.dictionaries;

import egr221a.datastructures.containers.Item;
import egr221a.interfaces.misc.DeletelessDictionary;
import egr221a.interfaces.misc.SimpleIterator;

import java.util.Iterator;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. The list is typically not sorted.
 * 2. Add new items to the front oft he list.
 * 3. Whenever find is called on an item, move it to the front of the 
 *    list. This means you remove the node from its current position 
 *    and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 *    elements to the front.  The iterator should return elements in
 *    the order they are stored in the list, starting with the first
 *    element in the list.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    private ItemNode front;

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        V oldValue = find(key);
        if (oldValue != null) {
            front.value = value;
        } else {
            if (front == null) {
                front = new ItemNode(key, value);
            } else {
                ItemNode newNode = new ItemNode(key, value);
                newNode.next = front;
                front = newNode;
            }
            size++;
        }
        return oldValue;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        V ret = null;
        ItemNode prev = new ItemNode(null, null);
        prev.next = front;
        ItemNode curr = front;
        while (curr != null) {
            if (curr.key.equals(key)) {
                ret = curr.value;
                if (curr != front) {
                    prev.next = curr.next;
                    curr.next = front;
                    front = curr;
                }
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        return ret;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new MoveToFrontListIterator();
    }

    private class ItemNode extends Item<K, V> {
        public ItemNode next;

        public ItemNode(K key, V value) {
            super(key, value);
            next = null;
        }
    }

    private class MoveToFrontListIterator extends SimpleIterator<Item<K, V>> {
        private ItemNode curr;

        public MoveToFrontListIterator() {
            curr = front;
        }

        @Override
        public Item<K, V> next() {
            Item<K, V> it = new Item<K, V>(curr.key, curr.value);
            curr = curr.next;
            return it;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }
    }
}

