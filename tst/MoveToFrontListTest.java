import datastructures.dictionaries.MoveToFrontList;
import egr221a.datastructures.containers.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by mhan on 4/7/2017.
 */
public class MoveToFrontListTest {
    @Test
    public void insertTest(){
        MoveToFrontList<Integer, Integer> t = new MoveToFrontList<>();
        Map<Integer, Integer> javaMap = new HashMap<>();
        Assert.assertTrue(t.size() == javaMap.size());

        final int NUMS = 1000;
        final int GAP =  37;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) {
            Integer expectedPrevValue = null;
            if(javaMap.containsKey(i)){
                expectedPrevValue = javaMap.get(i);
            }
            Integer prevValue = t.insert(i, i*2);

            Iterator<Item<Integer, Integer>> iter = t.iterator();
            Item<Integer,Integer> first = iter.next();
            Assert.assertTrue(first.key.equals(i));
            Assert.assertTrue(first.value.equals(i*2));

            Assert.assertTrue( expectedPrevValue == null && prevValue == null || expectedPrevValue.equals(prevValue));
            javaMap.put(i, i*2);
            Assert.assertTrue(t.find(i).equals(javaMap.get(i)));
            Assert.assertTrue(t.size() == javaMap.size());
        }

        Iterator<Item<Integer,Integer>> iterator = t.iterator();
        List<Integer> list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next().key);
        }
        int repeat = 10;
        int i = 0;
        while(i < repeat) {
            Collections.shuffle(list);

            for (Integer key : list) {
                Assert.assertTrue(t.find(key) != null);
                Iterator<Item<Integer, Integer>> iter = t.iterator();
                Item<Integer, Integer> first = iter.next();
                Assert.assertTrue(first.key.equals(key));
            }
            i++;
        }
    }

    @Test
    public void insertTest1(){
        MoveToFrontList<Integer, Integer> t = new MoveToFrontList<>();
        Map<Integer, Integer> javaMap = new HashMap<>();
        Assert.assertTrue(t.size() == javaMap.size());

        final int NUMS = 200;
        for( int i = 0; i < NUMS ; i++) {
            int key = new Random().nextInt(10);
            Integer expectedPrevValue = null;
            if(javaMap.containsKey(key)){
                expectedPrevValue = javaMap.get(key);
            }
            Integer prevValue = t.insert(key, i*2);
            javaMap.put(key, i*2);
            Assert.assertTrue( ((expectedPrevValue == null ? "null" : expectedPrevValue) + ", " +
                            (prevValue == null ? null : prevValue)),
                    expectedPrevValue == null && prevValue == null || expectedPrevValue.equals(prevValue));

            Assert.assertTrue(t.find(key).equals(javaMap.get(key)));
            Assert.assertTrue(t.size() == javaMap.size());
        }
    }

    @Test
    public void insertTest2() {
        MoveToFrontList<Integer, Integer> t = new MoveToFrontList<>();
        try {
            t.insert(null, 10);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void insertTest3() {
        MoveToFrontList<Integer, Integer> t = new MoveToFrontList<>();
        try {
            t.insert(10, null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void findTest() {
        MoveToFrontList<Integer, Integer> t = new MoveToFrontList<>();
        try{
            t.find(null);
            Assert.fail();
        }catch(IllegalArgumentException e){
        }
    }

    @Test
    public void iteratorTest(){
        MoveToFrontList<Integer, Integer> t = new MoveToFrontList<>();
        t.insert(1, 10);
        Iterator<Item<Integer,Integer>> iter1 = t.iterator();
        Iterator<Item<Integer,Integer>> iter2 = t.iterator();
        Item<Integer,Integer> item1 = iter1.next();
        Item<Integer,Integer> item2 = iter2.next();

        Assert.assertTrue(item1 == item2); //should have the same memory address
        t.insert(1, 30);

        Iterator<Item<Integer,Integer>> iter3 = t.iterator();
        Item<Integer,Integer> item3 = iter3.next();
        Assert.assertTrue(item1 == item3); //should have the same memory address
        int i = 1;
        Assert.assertTrue(!iter3.hasNext());

        t.insert(2, 50);
        t.insert(3, 100);
        Iterator<Item<Integer,Integer>> iter4 = t.iterator();

        i = 1;
        Item<Integer,Integer> item4 = iter4.next();
        while(iter4.hasNext()) {
            item4 = iter4.next();
            i++;
        }
        Assert.assertEquals(i, t.size());
        Assert.assertTrue(item1 == item4); //should have the same memory address
    }
}
