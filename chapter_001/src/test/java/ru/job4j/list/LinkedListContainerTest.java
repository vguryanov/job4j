package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 12.05.2018.
 */
public class LinkedListContainerTest {
    private LinkedListContainer<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void forEachInvocation() throws Exception {
        int expected = 3;
        for (int i : list) {
            assertThat(i, is(expected--));
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addMethodInvocationThrowsCMExceptionWhileIterating() {
        Iterator iterator = list.iterator();
        list.add(4);
        iterator.next();
    }
}