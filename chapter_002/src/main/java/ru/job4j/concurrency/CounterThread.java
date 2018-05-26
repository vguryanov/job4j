package ru.job4j.concurrency;

/**
 * Created by User2 on 26.05.2018.
 */
public class CounterThread extends Thread {
    private Counter counter;
    private long iterationNumber;

    public CounterThread(Counter counter, long iterationNumber) {
        this.counter = counter;
        this.iterationNumber = iterationNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterationNumber; i++) {
            counter.increaseCounter();
        }
    }
}
