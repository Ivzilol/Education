package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void testBubbleSort(){
        int[] numbers = {34, 2, 0, 45, 76, 23, 1, 8, 897, 39, 54};
        Bubble.sort(numbers);
        int[] expectedArr = {0, 1, 2, 8, 23, 34, 39, 45, 54, 76, 897};
        Assert.assertArrayEquals(expectedArr, numbers);
    }

}