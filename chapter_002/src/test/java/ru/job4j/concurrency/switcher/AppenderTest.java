package ru.job4j.concurrency.switcher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 14.06.2018.
 */
public class AppenderTest {
    @Test
    public void run() throws Exception {
        Switcher switcher = new Switcher(4);
        new Appender(switcher, '1').start();
        new Appender(switcher, '2').start();
        Thread.sleep(10);
        System.out.println(switcher);
    }
}