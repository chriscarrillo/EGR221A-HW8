import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;
import egr221a.datastructures.containers.Item;
import egr221a.interfaces.misc.Dictionary;
import egr221a.types.AlphabeticString;
import egr221a.types.NGram;
import org.junit.Assert;
import org.junit.Test;
import p2.wordsuggestor.NGramToNextChoicesMap;

import java.util.function.Supplier;

/**
 * Created by Chris Carrillo on 4/10/17.
 */
public class TestCheckPoint1 {
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
        Assert.assertEquals("{\"foo bar baz\"={bop=1}, \"Howdy Hey Hello\"={fum=1}}", map.toString());
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
}
