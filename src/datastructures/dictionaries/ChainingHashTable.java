package datastructures.dictionaries;

import datastructures.worklists.ListFIFOQueue;
import egr221a.datastructures.containers.Item;
import egr221a.interfaces.misc.DeletelessDictionary;
import egr221a.interfaces.misc.Dictionary;

import egr221a.interfaces.misc.SimpleIterator;
import egr221a.interfaces.worklists.WorkList;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * ChainingHashTable.java creates a new ChainingHashTable. It overrides insert, find, and iterator.
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
            rehash(); // 376.6ms
            //hashCode(); // 382.4ms
        }
        return oldValue;
    }

    /**
     * Returns the value of the given associated key
     * @param key
     *            the key whose associated value is to be returned
     * @return value returns associated with the given key
     */
    @Override
    public V find(K key) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return null;
        }
        return table[index].find(key);
    }

    /**
     * Returns true if the table contains the key given
     * @param key the key the table may contain
     * @return true if the table contains the key given
     * @return false if the table does not contain the key given
     */
    public boolean containsKey(K key){
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index].find(key) == key) {
            return true;
        }
        return false;
    }

    /**
     * Gets the value with the given key
     * @param key the given key of the table
     * @return the value of the given key
     */
    public V get(K key) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return null;
        }
        return table[index].find(key);
    }

    /**
     * keySet returns a Set of all of the keys in the table
     * @return a set of all the keys in the table
     */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (Item<K, V> entry : this) {
            keySet.add(entry.key);
        }
        return keySet;
    }

    /**
     * values returns a Set of all values of the table
     * @return a Set of all the values in the table
     */
    public Set<V> values() {
        Set<V> values = new HashSet<V>();
        for (Item<K, V> entry : this) {
            values.add(entry.value);
        }
        return values;
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

    /**
     * Hash function that rehashes the table
     */
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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChainingHashTable<?, ?> that = (ChainingHashTable<?, ?>) o;

        if (primeIndex != that.primeIndex) return false;
        if (newChain != null ? !newChain.equals(that.newChain) : that.newChain != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        int result = newChain != null ? newChain.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(table);
        result = 31 * result + primeIndex;
        return result;
    }*/
}
