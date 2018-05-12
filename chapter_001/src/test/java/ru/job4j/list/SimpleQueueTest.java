package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 12.05.2018.
 */
public class SimpleQueueTest {
    private SimpleQueue<String> testQueue;

    @Before
    public void setUp() throws Exception {
        testQueue = new SimpleQueue<>();
        testQueue.push("1");
        testQueue.push("2");
    }

    @Test(expected = NullPointerException.class)
    public void poll() throws Exception {
        assertThat(testQueue.poll(), is("1"));
        assertThat(testQueue.poll(), is("2"));
        testQueue.poll();
    }
}