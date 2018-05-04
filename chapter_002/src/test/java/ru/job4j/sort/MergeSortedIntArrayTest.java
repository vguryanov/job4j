package ru.job4j.sort;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 02.05.2018.
 */
public class MergeSortedIntArrayTest {
    @Test
    public void sortedMergeTest() {
        int[] array1 = new int[]{1, 5, 3, 0, 42, 232, -1};
        int[] array2 = new int[]{11, 2, 6, 4};
        int[] result = new MergeSortedIntArray(array1, array2).getValues();
        int[] expected = new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 11, 42, 232};
        assertThat(result, is(expected));
    }
}