package ru.job4j.timecomplexity;

import java.util.Arrays;

/**
 * Created by User2 on 25.05.2018.
 */
public class CharsComparator {
    private String s1, s2;

    public CharsComparator(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * method with linear time complexity
     */
    public boolean compareWLTC() {
        if (!isLengthsEquals()) {
            return false;
        }
        byte[] bytes1 = new byte[256];
        for (char b : s1.toCharArray()) {
            bytes1[b]++;
        }
        byte[] bytes2 = new byte[256];
        for (char b : s2.toCharArray()) {
            bytes2[b]++;
        }
        return Arrays.equals(bytes1, bytes2);
    }

    /**
     * method with quadratic time complexity
     */
    public boolean compareWQTC() {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        bubbleSort(chars1);
        bubbleSort(chars2);
        return Arrays.equals(chars1, chars2);
    }

    private static void bubbleSort(char[] array) {
        for (int i = 0; i < (array.length - 1); ++i) {
            for (int j = i + 1; j > 0; --j) {
                if (array[j] < array[j - 1]) {
                    char temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    private boolean isLengthsEquals() {
        return s1.length() == s2.length();
    }
}
