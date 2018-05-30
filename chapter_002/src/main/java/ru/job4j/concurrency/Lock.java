package ru.job4j.concurrency;

/**
 * Created by User2 on 30.05.2018.
 */
public class Lock {
    private boolean isLocked = false;
    private Thread lockedBy = null;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedBy = callingThread;
    }


    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            isLocked = false;
            notify();
        }
    }
}
