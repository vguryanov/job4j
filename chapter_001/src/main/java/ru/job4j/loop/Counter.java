package ru.job4j.loop;

/**
 * Created by User2 on 11.04.2018.
 */
public class Counter {
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }

        return result;
    }
}
