package ru.job4j.concurrency;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 26.05.2018.
 */
public class CounterThreadTest {
    @Test
    public void ifCounterNotSyncResultWillBeUnexpected() throws Exception {
        long mainIterationNumber = 2000;
        long threadIterationNumber = 1000;
        long expected = mainIterationNumber * threadIterationNumber;
        boolean isResultExpected = true;
        Counter counter = null;

        while (isResultExpected) {
            counter = new Counter();
            for (int i = 0; i < mainIterationNumber; i++) {
                new CounterThread(counter, threadIterationNumber).start();
            }
            Thread.sleep(2000);
            isResultExpected = counter.getValue() == expected;
        }

        assertTrue(null, !isResultExpected);
    }
}