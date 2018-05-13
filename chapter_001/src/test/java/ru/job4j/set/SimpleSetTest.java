package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 14.05.2018.
 */
public class SimpleSetTest {
    private static SimpleSet<String> testSet;
    String[] templateStrings = new String[]{"A", "B", "C"};

    @Before
    public void setUp() throws Exception {
        testSet = new SimpleSet<>();
        testSet.add(templateStrings[0]);
        testSet.add(templateStrings[1]);
        testSet.add(templateStrings[2]);
    }

    @Test
    public void afterCAddingSetContainsOnlyOneC() throws Exception {
        int cCount = 0;
        for (String s : testSet) {
            if (s.equals("C")) {
                cCount++;
            }
        }
        assertThat(cCount, is(1));
    }

    @Test
    public void forEachInvocation() throws Exception {
        int templateStringsCursor = 0;
        for (String s : testSet) {
            assertThat(s, is(templateStrings[templateStringsCursor]));
            templateStringsCursor++;
        }
    }
}