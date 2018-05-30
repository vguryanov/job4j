package ru.job4j.pool;

import java.util.Queue;

/**
 * Created by User2 on 30.05.2018.
 */
public class ConsumerThread extends Thread {
    private ThreadPool pool;

    public ConsumerThread(ThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                pool.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
