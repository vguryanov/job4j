package ru.job4j.concurrency.switcher;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by User2 on 14.06.2018.
 */
class Appender extends Thread {
    private char value;
    private Switcher switcher;

    public Appender(Switcher switcher, char value) {
        this.value = value;
        this.switcher = switcher;
    }

    @Override
    public void run() {
        ReentrantLock lock = switcher.getLock();
        while (!isInterrupted()) {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    switcher.append(value);
                }
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
