import datastructures.dictionaries.AVLTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by mhan on 4/7/2017.
 */
public class AVLTreeTest {
    @Test
    public void insertTest(){
        AVLTree<Integer,Integer> t = new AVLTree<>();
        Map<Integer, Integer> javaMap = new HashMap<>();
        Assert.assertTrue(t.size() == javaMap.size());

        final int NUMS = 5000;
        final int GAP =  37;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
            Integer expectedPrevValue = null;
            if(javaMap.containsKey(i)){
                expectedPrevValue = javaMap.get(i);
            }
            Integer prevValue = t.insert(i, i*2);

            Assert.assertTrue( expectedPrevValue == null && prevValue == null || expectedPrevValue.equals(prevValue));
            javaMap.put(i, i*2);
            Assert.assertTrue(t.find(i).equals(javaMap.get(i)));
            Assert.assertTrue(t.size() == javaMap.size());
            t.checkBalance();
        }
        System.out.println(t.size());
    }

    @Test
    public void insertTest1(){
        AVLTree<Integer,Integer> t = new AVLTree<>();
        Map<Integer, Integer> javaMap = new HashMap<>();
        Assert.assertTrue(t.size() == javaMap.size());

        final int NUMS = 200;
//        int[] keys = {1, 7, 6};
//        for(int k : keys) {
//            t.insert(k, 10);
//        }

        for( int i = 0; i < NUMS ; i++) {
            int key = new Random().nextInt(10);
            System.out.println("key" + key);
            Integer expectedPrevValue = null;
            if(javaMap.containsKey(key)){
                expectedPrevValue = javaMap.get(key);
            }
            Integer prevValue = t.insert(key, i*2);
            javaMap.put(key, i*2);
            System.out.println("value" + i*2);
            Assert.assertTrue( ((expectedPrevValue == null ? "null" : expectedPrevValue) + ", " +
                            (prevValue == null ? null : prevValue)),
                    expectedPrevValue == null && prevValue == null || expectedPrevValue.equals(prevValue));

            Assert.assertTrue(t.find(key).equals(javaMap.get(key)));
            Assert.assertTrue(t.size() == javaMap.size());
            t.checkBalance();
        }
    }

    @Test
    public void insertTest2() {
        AVLTree<Integer, Integer> t = new AVLTree<>();
        try {
            t.insert(null, 10);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void insertTest3() {
        AVLTree<Integer, Integer> t = new AVLTree<>();
        try {
            t.insert(10, null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }

    }

    @Test
    public void findTest() {
        AVLTree<Integer, Integer> t = new AVLTree<>();
        try{
            t.find(null);
            Assert.fail();
        }catch(IllegalArgumentException e){
        }
    }

    @Test
    public void heightTest(){
        AVLTree<Integer, Integer> t = new AVLTree<>();
        Assert.assertTrue(t.height() == -1);
        t.insert(5, 5);
        Assert.assertTrue(t.height() == 0);
        t.insert(2, 2);
        Assert.assertTrue(t.height() + "", t.height() == 1);
        t.insert(15, 15);
        Assert.assertTrue(t.height() + "", t.height() == 1);
        t.insert(1, 1);
        Assert.assertTrue(t.height() + "", t.height() == 2);
        t.insert(3, 3);
        Assert.assertTrue(t.height() == 2);
        t.insert(10, 10);
        Assert.assertTrue(t.height() == 2);
        t.insert(20, 20);
        Assert.assertTrue(t.height() == 2);
        t.insert(13, 13);
        Assert.assertTrue(t.height() == 3);
        t.insert(11, 11);
        Assert.assertTrue(t.height() == 3);
    }

//     CUT BELOW TWO METHODS AND PASTE INTO AVLTree.java (so that these methods become the instance method of AVLTree.java)
//    /** Below Methods are just for JUnit Test **/
//    //ONLY FOR JUNIT Testing
//    public void checkBalance( ) {
//        checkBalance(root);
//    }
//
//    //ONLY FOR JUNIT Testing
//    private int checkBalance(BSTNode t) {
//        if( t == null ) return -1;
//
//        if( t != null ) {
//            int hl = checkBalance(t.children[0]);
//            int hr = checkBalance(t.children[1]);
//            if( Math.abs( height( t.children[0] ) - height( t.children[1] ) ) > 1 ||
//                    height( t.children[0] ) != hl || height( t.children[1] ) != hr ||
//                    height( t.children[0]) != heightRecursion( t.children[0]) ||
//                    height( t.children[1]) != heightRecursion( t.children[1]))
//                throw new IllegalStateException("Need to fix your code");
//        }
//        return height( t );
//    }
//
//    //ONLY FOR JUNIT Testing
//    public int height(){
//        return height(root); //you should have this height helper defined in AVLTree.java (the height helper shouldn't use recursion) which returns the height given the root
//    }
//
//    //ONLY FOR JUNIT Testing
//    private int heightRecursion(BSTNode node){
//        if(node == null) return -1;
//        return Math.max( heightRecursion(node.children[0]), heightRecursion(node.children[1])) + 1;
//    }

}
