package ru.job4j.array;

import java.util.Arrays;

/**
 * Created by User2 on 13.04.2018.
 */
public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int duplicateCount = 0;

        for (int h = 0; h < array.length; h++) {
            duplicateCount = 0;
            for (int i = array.length - 1; i > 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (array[i].equals(array[j])) {
                        duplicateCount++;
                        break;
                    } else if (j == 0) {
                        for (int k = i; k > 0; k--) {
                            String proxy = array[k];
                            array[k] = array[k - 1];
                            array[k - 1] = proxy;
                        }
                    }
                }
            }
        }

        array = Arrays.copyOf(array, array.length - duplicateCount);
        return array;
    }
}
