package ru.job4j.loop;

/**
 * Created by User2 on 11.04.2018.
 */
public class Factorial {
    public int calc(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}
