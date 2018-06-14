package ru.job4j.concurrency.switcher;

/**
 * Created by User2 on 14.06.2018.
 */
public class Switcher {
    private final StringBuilder value;

    public Switcher(int i) {
        this.value = new StringBuilder(String.valueOf(i));
    }

    public void append(char c) {
        synchronized (value) {
            value.append(c);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
