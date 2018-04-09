package ru.job4j.calculator;

/**
 * Created by User2 on 09.04.2018.
 */
public class Calculator {
    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double minuend, double subtrahend) {
        this.result = minuend - subtrahend;
    }

    public void multiply(double first, double second) {
        this.result = first * second;
    }

    public void divide(double dividend, double divisor) {
        this.result = dividend / divisor;
    }

    public double getResult() {
        return this.result;
    }
}
