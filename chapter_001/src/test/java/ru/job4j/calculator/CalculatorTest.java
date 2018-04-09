package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();

        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;

        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractOneFromTwoThenOne() {
        Calculator calc = new Calculator();

        calc.subtract(2D, 1D);
        double result = calc.getResult();
        double expected = 1D;

        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyThreeByTwoThenSix() {
        Calculator calc = new Calculator();

        calc.multiply(3D, 2D);
        double result = calc.getResult();
        double expected = 6D;

        assertThat(result, is(expected));
    }

    @Test
    public void whenDivideFourByTwoThenTwo() {
        Calculator calc = new Calculator();

        calc.divide(4D, 2D);
        double result = calc.getResult();
        double expected = 2D;

        assertThat(result, is(expected));
    }
}