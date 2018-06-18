package ru.job4j.concurrency.switcher;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by User2 on 14.06.2018.
 */
public class Switcher {
    private final StringBuilder value;
    private ReentrantLock lock = new ReentrantLock(true);

    public Switcher(int i) {
        this.value = new StringBuilder(String.valueOf(i));
    }

    public ReentrantLock getLock() {
        return lock;
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
