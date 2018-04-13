package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * since 13.04.18
 */
public class FindLoopTest {
    @Test
    public void whenSearch5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;

        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasNo4ThenMinusOne() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3};
        int value = 4;
        int result = find.indexOf(input, value);
        int expect = -1;

        assertThat(result, is(expect));
    }
}