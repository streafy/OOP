package ru.nsu.fit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class HeapsortTests {

    @Test
    void testSimpleArray() {
        int[] array = {6, 3, 8, 11, 1};
        int[] expectedArray = {1, 3, 6, 8, 11};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testMinMaxArray() {
        int[] array= {3, 1, 2, Integer.MIN_VALUE, 4, Integer.MAX_VALUE};
        int[] expectedArray = {Integer.MIN_VALUE, 1, 2, 3, 4, Integer.MAX_VALUE};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testIntegerOverflow() {
        int[] array= {3, 1, 2, Integer.MIN_VALUE - 1, 4, Integer.MAX_VALUE + 1};
        int[] expectedArray = {Integer.MAX_VALUE + 1, 1, 2, 3, 4, Integer.MIN_VALUE - 1};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testDuplicateElements() {
        int[] array= {3, 1, 2, 2, 1, 1, 3, 2, 3};
        int[] expectedArray = {1, 1, 1, 2, 2, 2, 3, 3 ,3};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testSortedAscendingArray() {
        int[] array= {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testSortedDescendingArray() {
        int[] array= {5, 4, 3, 2, 1};
        int[] expectedArray = {1, 2, 3, 4, 5};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testSingleElementArray() {
        int[] array= {1};
        int[] expectedArray = {1};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testNegativeElementsArray() {
        int[] array= {-1, -2, -3, -4, -5};
        int[] expectedArray = {-5, -4, -3, -2, -1};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testEmptyArray() {
        int[] array = new int[5];
        int[] expectedArray = {0, 0, 0, 0, 0};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }
}
