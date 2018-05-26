package ru.job4j.concurrency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by User2 on 26.05.2018.
 */
@ThreadSafe
public class Counter {
    @GuardedBy("this")
    private long value = 0L;

    public synchronized void increment() {
        value++;
    }

    public synchronized long getValue() {
        return value;
    }

    @Override
    public synchronized String toString() {
        return String.valueOf(value);
    }
}
