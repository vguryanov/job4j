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
        if (queue.size() == 10) {
            System.out.println(Thread.currentThread().getName() + " queue is full, waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        notify();
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
        notify();
        return queue.poll();
    }
}