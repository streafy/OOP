package ru.nsu.fit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HeapsortTests {

    @Test
    @DisplayName("{ 6, 3, 8, 11, 1 } -> { 1, 3, 6, 8, 11 }")
    void sortSimpleArray() {
        int[] array = {6, 3, 8, 11, 1};
        int[] expectedArray = {1, 3, 6, 8, 11};

        Heapsort.sort(array);
        assertArrayEquals(expectedArray, array);
    }
}
