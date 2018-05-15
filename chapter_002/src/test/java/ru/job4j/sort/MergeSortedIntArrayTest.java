package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 02.05.2018.
 */
public class MergeSortedIntArrayTest {
    @Test
    public void sortedMergeTest() {
        int[] array1 = new int[]{1, 3, 42, 232};
        int[] array2 = new int[]{-1, 2, 4, 5, 28};
        String result = new MergeSortedIntArray(array1, array2).toString();
        String expected = Arrays.toString(new int[]{-1, 1, 2, 3, 4, 5, 28, 42, 232});
        assertThat(result, is(expected));
    }
}