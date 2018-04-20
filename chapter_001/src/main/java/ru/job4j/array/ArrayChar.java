package ru.job4j.array;

import java.util.Arrays;

/**
 * Created by User2 on 13.04.2018.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar() {
    }

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

    boolean contains(String origin, String sub) {
        char[] originChar = origin.toCharArray();
        char[] subChar = sub.toCharArray();
        for (int i = 0; i <= originChar.length - subChar.length; i++) {
            for (int j = 0; j < subChar.length; j++) {
                if (originChar[i + j] != subChar[j]) {
                    break;
                } else {
                    if (j == subChar.length - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
