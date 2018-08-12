package ru.job4j.java8;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 12.08.2018.
 */
public class FizzBuzzGenTest {
    private int count = 1;

    @Test
    public void generate() throws Exception {
        FizzBuzzGen.generate()
                .forEach(result -> {
                    String expected;
                    if (count % 15 == 0) {
                        expected = "FizzBuzz";
                    } else if (count % 5 == 0) {
                        expected = "Buzz";
                    } else if (count % 3 == 0) {
                        expected = "Fizz";
                    } else {
                        expected = String.valueOf(count);
                    }
                    assertThat(result, is(expected));
                    count++;
                });
    }
}