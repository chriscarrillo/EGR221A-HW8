import org.junit.Assert;
import org.junit.Test;
import p2.sorts.HeapSort;
import p2.sorts.QuickSort;
import p2.sorts.TopKSort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chris on 4/24/17.
 */
public class SortingTests {
    private Random rand = new Random();

    // Private helper method that creates a random array with given size and maximum
    private static Integer[] randomArray(int size, int max) {
        Integer[] randoms = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++)
            randoms[i] = rand.nextInt() % max;
        return randoms;
    }

    @Test
    public void quickSortTests() {
        for (int i = 1; i <= 5; i++) {
            Integer[] arr = randomArray(rand.nextInt(50) + 1, rand.nextInt(1000) + 1);
            Integer[] exp = arr.clone();
            Arrays.sort(exp);
            QuickSort.sort(arr);
            System.out.println("Expected " + i + ": " + Arrays.toString(exp));
            System.out.println("Actual   " + i + ": " + Arrays.toString(arr) + "\n");
            Assert.assertArrayEquals(exp, arr);
        }
    }

    @Test
    public void heapSortTests() {
        for (int i = 1; i <= 5; i++) {
            Integer[] arr = randomArray(rand.nextInt(50) + 1, rand.nextInt(1000) + 1);
            Integer[] exp = arr.clone();
            Arrays.sort(exp);
            HeapSort.sort(arr);
            System.out.println("Expected " + i + ": " + Arrays.toString(exp));
            System.out.println("Actual   " + i + ": " + Arrays.toString(arr) + "\n");
            Assert.assertArrayEquals(exp, arr);
        }
    }

    @Test
    public void topKSortTests() {
        for (int i = 1; i <= 5; i++) {
            Integer[] arr = randomArray(rand.nextInt(50) + 1, rand.nextInt(1000) + 1);
            Integer[] exp = arr.clone();
            Arrays.sort(exp);
            TopKSort.sort(arr, arr.length);
            System.out.println("Expected " + i + ": " + Arrays.toString(exp));
            System.out.println("Actual   " + i + ": " + Arrays.toString(arr) + "\n");
            Assert.assertArrayEquals(exp, arr);
        }
    }
}
