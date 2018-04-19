package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by User2 on 11.04.2018.
 */
public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max max = new Max();
        int first = 1;
        int second = 2;
        int result = max.max(first, second);

        assertThat(result, is(second));
    }

    @Test
    public void whenFirstMoreThanSecondAndThird() {
        Max max = new Max();
        int first = 3;
        int second = 2;
        int third = 1;
        int result = max.max(first, second, third);

        assertThat(result, is(first));
    }
}