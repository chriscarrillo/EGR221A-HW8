package datastructures.dictionaries;

import datastructures.worklists.ListFIFOQueue;
import egr221a.datastructures.containers.Item;
import egr221a.interfaces.misc.DeletelessDictionary;
import egr221a.interfaces.misc.Dictionary;
import egr221a.interfaces.misc.SimpleIterator;
import egr221a.interfaces.worklists.WorkList;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You must implement a generic chaining hashtable. You may not
 *    restrict the size of the input domain (i.e., it must accept 
 *    any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 *    shown in class).
 * 5. HashTable should be able to grow at least up to 200,000 elements. 
 * 6. We suggest you hard code some prime numbers. You can use this
 *    list: http://primes.utm.edu/lists/small/100000.txt 
 *    NOTE: Do NOT copy the whole list!
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private Supplier<Dictionary<K, V>> newChain;
    private Dictionary<K, V>[] table;
    private int primeIndex;
    private final static int[] PRIMES = {101, 211, 409, 821, 1657, 3299, 6599, 12163, 24391, 48821, 97073, 202309, 406981, 813647, 1621079};

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        primeIndex = 0;
        table = new Dictionary[PRIMES[primeIndex]];
    }

    @Override
    public V insert(K key, V value) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            table[index] = newChain.get();
        }
        V oldValue = table[index].find(key);
        if (oldValue == null) {
            size++;
        }
        table[index].insert(key, value);
        if (size >= table.length) {
            primeIndex++;
            rehash();
        }
        return oldValue;
    }

    @Override
    public V find(K key) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return null;
        }
        return table[index].find(key);
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new ChainingHashTableIterator();
    }

    private class ChainingHashTableIterator extends SimpleIterator<Item<K, V>> {
        private WorkList<Item<K, V>> list;

        public ChainingHashTableIterator() {
            initialize();
        }

        @Override
        public Item<K, V> next() {
            return list.next();
        }

        @Override
        public boolean hasNext() {
            return !(list.size() == 0);
        }

        private void initialize() {
            list = new ListFIFOQueue<Item<K, V>>();
            for (int i = 0; i < table.length; i++) {
                Dictionary<K, V> dictionary = table[i];
                if (dictionary != null) {
                    for (Item<K, V> it : dictionary) {
                        list.add(it);
                    }
                }
            }
        }

    }
    private void rehash() {
        Dictionary<K, V>[] newTable = new Dictionary[PRIMES[primeIndex]];
        for (Item<K, V> item : this) {
            int index = Math.abs(item.key.hashCode() % newTable.length);
            if (newTable[index] == null) {
                newTable[index] = newChain.get();
            }
            newTable[index].insert(item.key, item.value);
        }
        table = newTable;

    }
}
