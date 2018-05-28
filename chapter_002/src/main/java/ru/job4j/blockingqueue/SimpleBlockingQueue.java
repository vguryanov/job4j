package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by User2 on 29.05.2018.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() {
        if (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " queue is empty, waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}