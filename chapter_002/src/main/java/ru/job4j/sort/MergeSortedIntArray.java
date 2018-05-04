package ru.job4j.sort;

import java.util.Arrays;

/**
 * Created by User2 on 02.05.2018.
 */
public class MergeSortedIntArray {
    int[] values;
    int[] array1;
    int[] array2;

    public MergeSortedIntArray(int[] array1, int[] array2) {
        this.array1 = array1;
        this.array2 = array2;
        sortingMerge(array1, array2);
    }

    public int[] getValues() {
        return values;
    }

    private void sortingMerge(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        sort(array1);
        sort(array2);

        for (int i = 0, j = 0, k = 0; k < array1.length + array2.length; k++) {
            if (i < array1.length && j >= array2.length || array1[i] < array2[j]) {
                result[k] = array1[i];
                i++;
            } else {
                result[k] = array2[j];
                j++;
            }
        }
        values = result;
    }

    private int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    public static void main(String[] args) {
    }
}
