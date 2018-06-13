package ru.job4j.concurrency;

/**
 * Created by User2 on 13.06.2018.
 */
public class Deadlock {
    private static Object lockA = new Object(), lockB = new Object();
    private static TwinCounter counter1 = new TwinCounter();

    public static void main(String[] args) {
        new TwinIncrementor(counter1, false).start();
        new TwinIncrementor(counter1, true).start();
    }

    public static class TwinCounter {
        private volatile Long value1 = 0L, value2 = 0L;

        public void incrementBy1000() {
            synchronized (lockA) {
                for (int i = 0; i < 1000; i++) {
                    value1++;
                }
                System.out.println(Thread.currentThread().getName() + " is waiting for lockB...");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + " incrementing v2");
                    for (int i = 0; i < 1000; i++) {
                        value2++;
                    }
                }
            }
        }

        public void incrementBy10() {
            synchronized (lockB) {
                for (int i = 0; i < 10; i++) {
                    value1++;
                }
                System.out.println(Thread.currentThread().getName() + " is waiting for lockA...");
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + " incrementing v1");
                    for (int i = 0; i < 10; i++) {
                        value2++;
                    }
                }
            }
        }
    }

    public static class TwinIncrementor extends Thread {
        private TwinCounter counter;
        private boolean isFast;

        public TwinIncrementor(TwinCounter counter, boolean isFast) {
            this.counter = counter;
            this.isFast = isFast;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                if (isFast) {
                    counter.incrementBy10();
                } else {
                    counter.incrementBy1000();
                }
            }
        }
    }
}
