package ru.job4j.array;

/**
 * Created by User2 on 13.04.2018.
 */
public class Matrix {
    public int[][] multiple(int size) {
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = (i + 1) * (j + 1);
            }
        }

        return result;
    }
}
