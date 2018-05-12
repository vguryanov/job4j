package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 12.05.2018.
 */
public class SimpleStackTest {
    SimpleStack<String> testStack;

    @Before
    public void setUp() throws Exception {
        testStack = new SimpleStack<>();
        testStack.push("1");
        testStack.push("2");
    }

    @Test(expected = NullPointerException.class)
    public void poll() throws Exception {
        assertThat(testStack.poll(), is("2"));
        assertThat(testStack.poll(), is("1"));
        testStack.poll();
    }
}