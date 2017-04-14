import datastructures.dictionaries.AVLTree;
import egr221a.datastructures.containers.Item;
import egr221a.interfaces.misc.Dictionary;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Christopher on 4/10/17.
 */
public class AVLTreeTestMine {

    @Test
    public void insertTest() {
        AVLTree<String, Integer> tree = new AVLTree<>();

        tree.insert("hello", 1);
        tree.insert("wow", 2);
        tree.insert("cool", 3);
        tree.insert("awesome", 4);
        tree.insert("hola", 5);

        System.out.println(tree);

        tree.insert("chris",6);

        System.out.println(tree);

        System.out.println(tree.find("hola"));
        System.out.println(tree.find("wow"));


    }


    @Test
    public void testTrees() {
        AVLTree<String, Integer> tree = initiate();
        String[] testStruct = { "a", "b", "c", "d", "e" };
        String[] tests = { "b", "d", "e", "c", "a" };
        for (int i = 0; i < 5; i++) {
            String str = tests[i] + "a";
            count(tree, str);
        }

        boolean passed = true;
        int i = 0;
        for (Item<String, Integer> item : tree) {
            String stringHeap = item.key;
            String string = testStruct[i] + "a";
            passed &= string.equals(stringHeap);
            i++;
        }

        Assert.assertEquals(true, passed);
    }
    protected static AVLTree<String, Integer> initiate() {
        AVLTree<String, Integer> tree = new AVLTree<>();

        return tree;
    }

    private static <E extends Comparable<E>> void count(Dictionary<E, Integer> tree, E key) {
        Integer value = tree.find(key);
        if (value == null) {
            tree.insert(key, 1);
        } else {
            tree.insert(key, value + 1);
        }
    }

}
