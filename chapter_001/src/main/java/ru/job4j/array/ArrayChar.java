package ru.job4j.array;

import java.util.Arrays;

/**
 * Created by User2 on 13.04.2018.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    public ArrayChar(char[] line) {
        this.data = line;
    }

    /**
     * Проверяет. что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();

        for (int i = 0; i < value.length; i++) {
            if (data[i] != value[i]) {
                return false;
            }
        }

        return result;
    }

    boolean contains(String sub) {
        if (sub.length() > data.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i <= data.length - sub.length(); i++) {
            if (data[i] == sub.charAt(0)) {
                ArrayChar substring = new ArrayChar(Arrays.copyOfRange(data, i, data.length));

                if (substring.startWith(sub)) {
                    return true;
                }
            }
        }

        return false;
    }
}
