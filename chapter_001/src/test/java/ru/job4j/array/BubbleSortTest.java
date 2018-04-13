package ru.job4j.array;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * since 13.04.18
 */
public class BubbleSortTest {
    @Test
    public void sort() throws Exception {
        int[] array = new int[]{3, 4, 1, 5, 9, 6, 10, 8, 2, 7};
        int[] result = new BubbleSort().sort(array);
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        assertThat(result, is(expected));
    }
}