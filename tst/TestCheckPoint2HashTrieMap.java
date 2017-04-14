import datastructures.dictionaries.HashTrieMap;
import egr221a.types.AlphabeticString;
import org.junit.Assert;
import org.junit.Test;

/**
 * Feel free to add more tests!
 * @author Chris Nugent, Mikyung Han
 */
public class TestCheckPoint2HashTrieMap {

    public static String[] testKeyStr1 = {"bat", "batter", "bath"};
    public static int[] testValueStr1 = {0, 1, 2};

    private AlphabeticString convert(String s) {
        return new AlphabeticString(s);
    }

    @Test
    public void insertTest() {
        HashTrieMap<Character, AlphabeticString, String> map = new HashTrieMap(AlphabeticString.class);
        map.insert(convert("apple"), "banana");
        map.insert(convert("orange"), "orange");
        map.insert(convert("lemons"), "limes");

        Assert.assertEquals("banana", map.find(convert("apple")));
        Assert.assertEquals("orange", map.find(convert("orange")));
        Assert.assertEquals("limes", map.find(convert("lemons")));
    }

    @Test
    public void test1() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            map.insert(convert( testKeyStr1[i] ), testValueStr1[i]);
            Assert.assertEquals(i+1, map.size());
            Assert.assertEquals(testValueStr1[i] , (int) map.find(convert(testKeyStr1[i])));
            Assert.assertTrue( map.findPrefix(convert( testKeyStr1[i] )));
        }

        String[] testString2 = {"", "b", "ba", "batt", "batte", "batterr"};
        for(String str : testString2) {
            Assert.assertTrue(map.find(convert(str)) == null);
            map.delete(convert(str));
            Assert.assertEquals(3, map.size());
        }

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            Assert.assertEquals(testValueStr1[i] , (int) map.find(convert(testKeyStr1[i])));
        }

        String[] testString3 = {"", "b", "ba", "bat", "bath", "batt", "batte", "batter"};
        for(String str : testString3) {
            Assert.assertTrue(map.findPrefix(convert(str)));
        }

        Assert.assertFalse( map.findPrefix(convert("batterr")));

        Assert.assertEquals(3, map.size());
    }

    @Test
    public void test2() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            map.insert(convert( testKeyStr1[i] ), testValueStr1[i]);
        }

        Assert.assertEquals( testValueStr1[0], (int) map.insert(convert(testKeyStr1[0]), 3));
        Assert.assertEquals(3, map.size());
        Assert.assertEquals( testValueStr1[1], (int) map.insert(convert(testKeyStr1[1]), 4));
        Assert.assertEquals(3, map.size());
        Assert.assertEquals( testValueStr1[2], (int) map.insert(convert(testKeyStr1[2]), 5));
        Assert.assertEquals(3, map.size());

    }

    @Test
    public void test3() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);
        try {
            map.insert(null, 1);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void test4() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);
        try {
            map.insert(convert("test"), null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void test5() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);
        try {
            map.find(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void test6() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);
        try {
            map.findPrefix(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void test7() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            map.insert(convert( testKeyStr1[i] ), testValueStr1[i]);
        }

        map.delete(convert("bat"));
        Assert.assertEquals(2, map.size());
        Assert.assertTrue( map.find(convert("bat")) == null);

        for(int i = 1 ; i < testKeyStr1.length ; i++) {
            Assert.assertEquals(testValueStr1[i] , (int) map.find(convert(testKeyStr1[i])));
        }

        String[] testString3 = {"", "b", "ba", "bat", "bath", "batt", "batte", "batter"};
        for(String str : testString3) {
            Assert.assertTrue(map.findPrefix(convert(str)));
        }

        Assert.assertFalse( map.findPrefix(convert("batterr")));
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void test8() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            map.insert(convert( testKeyStr1[i] ), testValueStr1[i]);
        }

        map.delete(convert("bath"));
        Assert.assertEquals(2, map.size());
        Assert.assertTrue( map.find(convert("bath")) == null);
        Assert.assertTrue( !map.findPrefix(convert("bath")));

        for(int i = 0 ; i < testKeyStr1.length - 1 ; i++) {
            Assert.assertEquals(testValueStr1[i] , (int) map.find(convert(testKeyStr1[i])));
        }

        String[] testString3 = {"", "b", "ba", "bat", "batt", "batte", "batter"};
        for(String str : testString3) {
            Assert.assertTrue(map.findPrefix(convert(str)));
        }

        Assert.assertFalse( map.findPrefix(convert("batterr")));
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void test9() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            map.insert(convert( testKeyStr1[i] ), testValueStr1[i]);
        }

        map.delete(convert("batter"));
        Assert.assertEquals(2, map.size());
        Assert.assertTrue( map.find(convert("batter")) == null);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            if(i != 1)
                Assert.assertEquals(testValueStr1[i] , (int) map.find(convert(testKeyStr1[i])));
        }

        String[] testString3 = {"", "b", "ba", "bat", "bath"};
        for(String str : testString3) {
            Assert.assertTrue(str, map.findPrefix(convert(str)));
        }

        String[] testString4 = {"batt", "batte", "batter"};
        for(String str : testString4) {
            Assert.assertTrue(str, !map.findPrefix(convert(str)));
        }

        Assert.assertFalse( map.findPrefix(convert("batterr")));
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void test10() {
        HashTrieMap<Character, AlphabeticString, Integer> map = new HashTrieMap(AlphabeticString.class);

        for(int i = 0 ; i < testKeyStr1.length ; i++) {
            map.insert(convert( testKeyStr1[i] ), testValueStr1[i]);
        }

        map.delete(convert("bat"));
        Assert.assertEquals(2, map.size());

        Assert.assertTrue(map.findPrefix(convert("")));
        Assert.assertTrue(map.findPrefix(convert("b")));
        Assert.assertTrue(map.findPrefix(convert("ba")));
        Assert.assertTrue(map.findPrefix(convert("bat")));
        Assert.assertTrue(map.findPrefix(convert("bath")));
        Assert.assertTrue(map.findPrefix(convert("batt")));
        Assert.assertTrue(map.findPrefix(convert("batte")));
        Assert.assertTrue(map.findPrefix(convert("batter")));

        map.delete(convert("batter"));
        Assert.assertEquals(1, map.size());

        Assert.assertTrue(map.findPrefix(convert("")));
        Assert.assertTrue(map.findPrefix(convert("b")));
        Assert.assertTrue(map.findPrefix(convert("ba")));
        Assert.assertTrue(map.findPrefix(convert("bat")));
        Assert.assertTrue(map.findPrefix(convert("bath")));
        Assert.assertTrue(!map.findPrefix(convert("batt")));
        Assert.assertTrue(!map.findPrefix(convert("batte")));
        Assert.assertTrue(!map.findPrefix(convert("batter")));

        map.delete(convert("bath"));
        Assert.assertEquals(0, map.size());

        Assert.assertTrue(map.findPrefix(convert("")));
        Assert.assertTrue(!map.findPrefix(convert("b")));
        Assert.assertTrue(!map.findPrefix(convert("ba")));
        Assert.assertTrue(!map.findPrefix(convert("bat")));
        Assert.assertTrue(!map.findPrefix(convert("bath")));
        Assert.assertTrue(!map.findPrefix(convert("batt")));
        Assert.assertTrue(!map.findPrefix(convert("batte")));
        Assert.assertTrue(!map.findPrefix(convert("batter")));
    }
}