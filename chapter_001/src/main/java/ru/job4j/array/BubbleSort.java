package ru.job4j.array;

/**
 * Created by User2 on 13.04.2018.
 */
public class BubbleSort {
    public int[] sort(int[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int proxy = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = proxy;
                }
            }
        }

        return array;
    }
}
