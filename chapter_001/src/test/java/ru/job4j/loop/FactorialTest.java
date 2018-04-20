package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        //напишите здесь тест, проверяющий, что факториал для числа 5 равен 120.
        int factorial = new Factorial().calc(5);
        assertThat(factorial, is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        //напишите здесь тест, проверяющий, что факториал для числа 0 равен 1.
        int factorial = new Factorial().calc(0);
        assertThat(factorial, is(1));
    }
}