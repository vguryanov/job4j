package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by User2 on 30.05.2018.
 */
public class ThreadPool {
    private static final int CORE_AMOUNT = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>(CORE_AMOUNT);

    public ThreadPool() {
        for (int i = 0; i < CORE_AMOUNT; i++) {
            threads.add(new ConsumerThread(this));
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
        notifyAll();
    }

    public synchronized Runnable poll() throws InterruptedException {
        if (tasks.isEmpty()) {
            wait();
        }
        return tasks.poll();
    }

    public void shutdown() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
