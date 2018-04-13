package ru.job4j.array;

/**
 * Created by User2 on 13.04.2018.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
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
}
