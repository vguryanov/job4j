package ru.job4j.timecomplexity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 25.05.2018.
 */
public class CharsComparatorTest {
    private CharsComparator testComp1 = new CharsComparator("qwertyq", "werqtqy");
    private CharsComparator testComp2 = new CharsComparator("qwertyq", "werqtqyy");

    @Test
    public void compareWLTC() throws Exception {
        assertTrue("", testComp1.compareWLTC());
        assertTrue("", !testComp2.compareWLTC());
    }

    @Test
    public void compareWQTC() throws Exception {
        assertTrue("", testComp1.compareWQTC());
        assertTrue("", !testComp2.compareWQTC());
    }
}