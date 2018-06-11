package ru.job4j.blockingqueue;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 29.05.2018.
 */
public class SimpleBlockingQueueTest {
//    @Test
//    public void offer() throws Exception {
//        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
//        Thread producer = new Thread() {
//            @Override
//            public void run() {
//                while (!isInterrupted()) {
//                    int value = (int) (Math.random() * 100);
//                    queue.offer(value);
//                    System.out.println(Thread.currentThread().getName() + " adding " + value);
//                }
//            }
//        };
//        Thread consumer = new Thread() {
//            @Override
//            public void run() {
//                while (!isInterrupted()) {
//                    System.out.println(Thread.currentThread().getName() + " consume " + queue.poll());
//                }
//            }
//        };
//
//        producer.start();
//        consumer.start();
//        Thread.sleep(5);
//        producer.interrupt();
//        consumer.interrupt();
//    }
}