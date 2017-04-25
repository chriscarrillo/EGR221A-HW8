package p2.writeup;

import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;
import egr221a.datastructures.trees.BinarySearchTree;
import egr221a.interfaces.misc.Dictionary;
import egr221a.types.AlphabeticString;
import egr221a.types.NGram;
import p2.wordsuggestor.NGramToNextChoicesMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Created by Chris on 4/24/17.
 */
public class WriteUpTest {
    private static Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> outer =
            () -> new HashTrieMap<String, NGram, Dictionary<AlphabeticString, Integer>>(NGram.class);
    private static Supplier<Dictionary<AlphabeticString, Integer>> inner =
            () -> new HashTrieMap<Character, AlphabeticString, Integer>(AlphabeticString.class);

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("alice.txt"));
        long startTime = System.currentTimeMillis();
        //BinarySearchTree<String, Integer> tree = createTree(file, true);
        ChainingHashTable<String, Integer> list = buildChain(file);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + totalTime + "ms");
        //System.out.println("Tree Type: " + tree.getClass());
    }

    private static BinarySearchTree<String, Integer> createTree(Scanner file, boolean isBST) {
        BinarySearchTree<String, Integer> tree;
        if (isBST) {
            tree = new BinarySearchTree<>();
        } else {
            tree = new AVLTree<>();
        }

        HashMap<String, Integer> map = new HashMap<>();
        while (file.hasNext()) {
            String word = file.next().replaceAll("[.*\\W+.*]", "");
            if (map.containsKey(word))
                map.put(word, map.get(word) + 1);
            else
                map.put(word, 1);
        }

        for (String key : map.keySet()) {
            tree.insert(key, map.get(key));
        }

        System.out.println(tree.toString());

        return tree;
    }

    private static ChainingHashTable<String, Integer> buildChain(Scanner file) {
        ChainingHashTable<String, Integer> list = new ChainingHashTable<>(() -> new BinarySearchTree<String, Integer>());
        while (file.hasNext()) {
            String word = file.next().replaceAll("[.*\\W+.*]", "");
            if (list.find(word) == null)
                list.insert(word, 1);
            else
                list.insert(word, list.get(word) + 1);
        }
        System.out.println(list.toString());
        return list;
    }

    /*private static void buildHashTrieMap(Scanner file) {
        NGramToNextChoicesMap map = new NGramToNextChoicesMap(outer, inner);
        NGram[] nGrams = new NGram[];
        String[] words;

        while (file.hasNext()) {
            String word = file.next().replaceAll("[.*\\W+.*]", "");
        }

        for (int i = 0; i < nGrams.length; i++) {
            map.seenWordAfterNGram(nGrams[i], words[i]);
        }
    }*/
}
