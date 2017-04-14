package p2.clients;

import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import egr221a.interfaces.misc.Dictionary;
import egr221a.interfaces.trie.BString;
import egr221a.types.AlphabeticString;
import egr221a.types.NGram;
import p2.wordsuggestor.WordSuggestor;

import java.io.IOException;
import java.util.function.Supplier;

public class NGramTester {
    public static <A extends Comparable<A>, K extends BString<A>, V> Supplier<Dictionary<K, V>> trieConstructor(Class<K> clz) {
        return () -> new HashTrieMap<A, K, V>(clz);
    }

    public static <K, V> Supplier<Dictionary<K, V>> hashtableConstructor(
            Supplier<Dictionary<K, V>> constructor) {
        return () -> new ChainingHashTable<K, V>(constructor);
    }

//    public static <K, V> Supplier<Dictionary<K, V>> moveToFrontListListConstructor() {
//        return () -> new MoveToFrontList<K, V>();
//    }

    public static void main(String[] args) {
        try {
            WordSuggestor suggestions = new WordSuggestor("spoken.corpus", 2, -1,
                    NGramTester.trieConstructor(NGram.class),
                    NGramTester.trieConstructor(AlphabeticString.class));
            System.out.println(suggestions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}