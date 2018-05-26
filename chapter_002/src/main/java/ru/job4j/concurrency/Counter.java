package ru.job4j.concurrency;

/**
 * Created by User2 on 26.05.2018.
 */
public class Counter {
    private long value = 0L;

    public void increaseCounter() {
        value++;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
