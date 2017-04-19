import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;
import egr221a.datastructures.containers.Item;
import egr221a.interfaces.misc.Dictionary;
import egr221a.types.AlphabeticString;
import egr221a.types.NGram;
import org.junit.Assert;
import org.junit.Test;
import p2.sorts.HeapSort;
import p2.sorts.QuickSort;
import p2.sorts.TopKSort;
import p2.wordsuggestor.NGramToNextChoicesMap;

import java.util.function.Supplier;

/**
 * Created by Chris on 4/17/17.
 */
public class Checkpoint2Tests {
    /**
     * Test Cases for ChainingHashtable
     * @param list
     * @param key
     */
    private static void insertHelper(Dictionary<String, Integer> list, String key) {
        Integer find = list.find(key);
        if (find == null)
            list.insert(key, 1);
        else
            list.insert(key, 1 + find);
    }


    @Test
    public void Test1(){
        ChainingHashTable<String, Integer> list = new ChainingHashTable<>(() -> new MoveToFrontList<>());

        int n = 1000;

        // Add them -> insert()
        for (int i = 0; i < 5 * n; i++) {
            int k = (i % n) * 37 % n;
            String str = String.format("%05d", k);
            for (int j = 0; j < k + 1; j ++) {
                insertHelper(list, str);
            }
        }

        // Delete them
        boolean passed = true;
        int totalCount = 0;
        for (Item<String, Integer> integerItem : list) {
            passed &= (Integer.parseInt(integerItem.key) + 1) * 5 == integerItem.value;
            totalCount += integerItem.value;
        }

        passed &= totalCount == (n * (n + 1)) / 2 * 5;
        passed &= list.size() == n;
        passed &= list.find("00851") == 4260;


        Assert.assertEquals(passed,true);
    }

    // Tests for NGramToNextChoicesMap.java
    private static Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> outer =
            () -> new HashTrieMap<String, NGram, Dictionary<AlphabeticString, Integer>>(NGram.class);
    private static Supplier<Dictionary<AlphabeticString, Integer>> inner =
            () -> new HashTrieMap<Character, AlphabeticString, Integer>(AlphabeticString.class);

    @Test
    public void seenWordAfterNGramTest1() {
        NGramToNextChoicesMap map = new NGramToNextChoicesMap(outer, inner);
        NGram[] nGrams = new NGram[]{new NGram(new String[] {"foo", "bar", "baz"}), new NGram(new String[] {"Howdy", "Hey", "Hello"})};
        String[] words = new String[] {"bop", "fum"};

        for (int i = 0; i < nGrams.length; i++) {
            map.seenWordAfterNGram(nGrams[i], words[i]);
        }
        Assert.assertEquals("{\"Howdy Hey Hello\"={fum=1}, \"foo bar baz\"={bop=1}}", map.toString());
    }

    @Test
    public void getCountsAfterTest1() {
        NGramToNextChoicesMap map = new NGramToNextChoicesMap(outer, inner);
        NGram[] nGrams = new NGram[]{new NGram(new String[] {"foo", "bar", "baz"}), new NGram(new String[] {"Howdy", "Hey", "Hello"})};
        String[] words = new String[] {"bop", "fum"};

        for (int i = 0; i < nGrams.length; i++) {
            map.seenWordAfterNGram(nGrams[i], words[i]);
        }

        Item<String, Integer>[] items = map.getCountsAfter(nGrams[nGrams.length - 1]);
        Assert.assertEquals(items.length, 1);
    }

    // Tests for MoveToFrontList.java
    @Test
    public void insertTest1() {
        MoveToFrontList list = new MoveToFrontList();
        list.insert(1, "Hello");
        Assert.assertEquals(list.size(), 1);
        list.insert(2, "Howdy");
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void findTest1() {
        MoveToFrontList list = new MoveToFrontList();
        list.insert(1, "Hello");
        list.insert(2, "Howdy");
        list.insert(3, "Hi");
        list.insert(4, "Hey");
        list.find(4);
        Assert.assertEquals("{4=Hey, 3=Hi, 2=Howdy, 1=Hello}", list.toString());
    }

    // Tests for the sorts
    @Test
    public void quickSortTest1() {
        Integer[] array = {12, 4, 0, -23, -42, 87, 32, 19, -12, 7};
        Integer[] exp = {-42, -23, -12, 0, 4, 7, 12, 19, 32, 87};
        QuickSort.sort(array);
        Assert.assertArrayEquals(exp, array);
    }

    /*@Test
    public void heapSortTest1() {
        Integer[] array = {12, 4, 0, -23, -42, 87, 32, 19, -12, 7};
        Integer[] exp = {-42, -23, -12, 0, 4, 7, 12, 19, 32, 87};
        HeapSort.sort(array);
        Assert.assertArrayEquals(exp, array);
    }

    @Test
    public void topKSortTest1() {
        Integer[] array = {12, 4, 0, -23, -42, 87, 32, 19, -12, 7};
        Integer[] exp = {-42, -23, -12, 0, 4, 7, 12, 19, 32, 87};
        TopKSort.sort(array, array.length);
        Assert.assertArrayEquals(exp, array);
    }*/
}
