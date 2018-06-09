package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by User2 on 30.05.2018.
 */
public class ThreadPool {
    private static final int CORE_AMOUNT = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public ThreadPool() {
        for (int i = 0; i < CORE_AMOUNT; i++) {
            PoolThread p = new PoolThread();
            threads.add(p);
            p.start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    private class PoolThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    tasks.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.interrupt();
                }
            }
        }
    }
}
