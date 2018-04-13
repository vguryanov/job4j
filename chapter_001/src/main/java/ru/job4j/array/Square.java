package ru.job4j.array;

public class Square {
    public int[] calculate(int bound) {
        int[] rst = new int[bound];

        for (int i = 1; i <= bound; i++) {
            rst[i - 1] = i * i;
        }

        return rst;
    }
}