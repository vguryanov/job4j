package ru.job4j.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 11.05.2018.
 */
public class DynamicArrayTest {
    private DynamicArray<String> testArray;

    private void addElementsToTestArray(int count) {
        for (int i = 0; i < count; i++) {
            testArray.add("e" + i);
        }
    }

    @Before
    public void setUp() throws Exception {
        testArray = new DynamicArray<>();
        addElementsToTestArray(10);
    }

    @Test
    public void afterAddingTenMoreElementsSizeWillBeTwenty() throws Exception {
        addElementsToTestArray(10);
        assertThat(testArray.size(), is(20));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGrowsWhileIteratorWorkingThrowsCMException() throws Exception {
        Iterator iterator = testArray.iterator();
        testArray.add("");
        iterator.next();
    }

    @Test
    public void contains() throws Exception {
        assertTrue("", testArray.contains("e1"));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(testArray);
        System.out.println(testArray.size());
        testArray.delete("e4");
        System.out.println(testArray);
        System.out.println(testArray.size());
    }
}