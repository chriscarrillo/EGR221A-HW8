import egr221a.types.AlphabeticString;
import egr221a.types.NGram;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import p2.clients.NGramTester;
import p2.wordsuggestor.WordSuggestor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by mhan on 4/8/2017.
 */
public class NGramToNextChoicesMapTest {

    private static String[] fileNames;
    private static int[] Ns;
    private static String[] expectedStrings;

    private void checkHelper(String fileName, int N, String expectedStr){
        try {
            WordSuggestor suggestions = new WordSuggestor(fileName, N, -1,
                    NGramTester.trieConstructor(NGram.class),
                    NGramTester.trieConstructor(AlphabeticString.class));
            System.out.println("actual" + suggestions);
            System.out.println("expected" + expectedStr);
            checkSame(expectedStr, suggestions.toString());
        }catch(IOException e){
            Assert.fail(e.getMessage());
        }
    }

    private void checkSame(String str1, String str2){
        Map<String,Map<String,Integer>> map1 = buildMap(str1);
        Map<String,Map<String,Integer>> map2 = buildMap(str2);
        Assert.assertTrue(map1.equals(map2));
    }

    private Map<String,Map<String,Integer>> buildMap(String str){
        Map<String,Map<String,Integer>> map = new HashMap<>();

        while(!str.isEmpty()) {
            String outerKey = "";
            int startKey = str.indexOf("\"");
            int endKey = str.indexOf("\"", startKey + 1);
            if(startKey != -1 && endKey != -1){
                outerKey = str.substring(startKey+1, endKey);
            } else {
                throw new IllegalStateException("error building outer map key");
            }
            String outerValue = "";
            int startValue = str.indexOf("\"={");
            int endValue = str.indexOf("}", startValue + 1);
            if(startValue != -1 && endValue != -1) {
                outerValue = str.substring(startValue + 3, endValue);
                Map<String, Integer> innerMap = buildInnerMap(outerValue);

                map.put(outerKey, innerMap);
            } else {
                throw new IllegalStateException("error building outer map value");
            }
            str = str.substring(endValue + 2);
        }
        return map;
    }

    private Map<String,Integer> buildInnerMap(String str){
        Map<String,Integer> map = new HashMap<>();
        String[] pairs = str.split(",");
        for(String s : pairs) {
            s = s.replaceAll(" ", ""); //remove white space
            String[] innerPair = s.split("=");
            Assert.assertTrue(innerPair.length == 2);
            map.put(innerPair[0], Integer.parseInt(innerPair[1]));
        }
        return map;
    }

    @BeforeClass
    public static void setupOnce(){
        try {
            Scanner scanner = new Scanner(new File("expected.txt"));
            fileNames = new String[5];
            Ns = new int[5];
            expectedStrings = new String[5];
            int i = 0;
            while (scanner.hasNextLine()) {
                fileNames[i] = scanner.nextLine();
                Ns[i] = Integer.parseInt(scanner.nextLine());
                expectedStrings[i] = scanner.nextLine();
                i++;
            }
        }catch(FileNotFoundException e){
            Assert.fail("expected.txt not found! Place download expected.txt from google drive and put it under root of the directory");
        }
    }

    @Test
    public void test1(){
        checkHelper(fileNames[0], Ns[0], expectedStrings[0]);
    }

    @Test
    public void test2(){
        checkHelper(fileNames[1], Ns[1], expectedStrings[1]);
    }

    @Test
    public void test3(){
        checkHelper(fileNames[2], Ns[2], expectedStrings[2]);
    }

    @Test
    public void test4(){
        checkHelper(fileNames[3], Ns[3], expectedStrings[3]);
    }

    @Test
    public void test5(){
        checkHelper(fileNames[4], Ns[4], expectedStrings[4]);
    }
}
