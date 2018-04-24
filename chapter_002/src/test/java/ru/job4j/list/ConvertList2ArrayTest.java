package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void convert() throws Exception {
        List<int[]> testList = new ArrayList<>();
        testList.add(new int[]{1, 2, 3});
        testList.add(new int[]{4, 5, 6});
        testList.add(new int[]{7, 8, 9});

        List<Integer> result = new ConvertList2Array().convert(testList);

        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            expected.add(i);
        }

        assertThat(result, is(expected));
    }

    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }
}