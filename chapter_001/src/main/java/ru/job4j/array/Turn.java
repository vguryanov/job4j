package ru.job4j.array;

public class Turn {
    public int[] turn(int[] array) {
        for (int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
            int proxy = array[i];
            array[i] = array[j];
            array[j] = proxy;
        }

        return array;
    }
}