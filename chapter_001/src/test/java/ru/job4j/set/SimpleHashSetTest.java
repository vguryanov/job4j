package ru.job4j.set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.DynamicArray;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by User2 on 15.05.2018.
 */
public class SimpleHashSetTest {
    private static SimpleHashSet<String> testSet;
    private int initialElementsAmount = 20;
    private int count;

    private void addTestElements(int amount) {
        int lastCount = count;
        for (int i = count; i < amount + lastCount; i++) {
            testSet.add("e" + count);
            count++;
        }
    }

    private int getCapacityOfFirstHashtableLine() throws Exception {
        Field innerHashTable = testSet.getClass().getDeclaredField("table");
        innerHashTable.setAccessible(true);
        DynamicArray<String>[] table = (DynamicArray<String>[]) innerHashTable.get(testSet);
        DynamicArray tableLine = table[0];
        Field tableLineInnerArray = tableLine.getClass().getDeclaredField("container");
        tableLineInnerArray.setAccessible(true);
        Object[] innerArray = (Object[]) tableLineInnerArray.get(tableLine);
        return innerArray.length;
    }

    @Before
    public void setUp() throws Exception {
        testSet = new SimpleHashSet<>();
        count = 0;
        addTestElements(initialElementsAmount);
    }

    @Test
    public void afterAddingMoreThanHundredElementsCapacityChanges() throws Exception {
        assertTrue("", getCapacityOfFirstHashtableLine() <= initialElementsAmount);
        addTestElements(1000);
        assertTrue("", getCapacityOfFirstHashtableLine() > initialElementsAmount);
    }

    @Test
    public void containsMethodReturnsTrueForE1() throws Exception {
        assertTrue("containsMethodReturnsTrueForE1 test failed", testSet.contains("e1"));
    }

    @Test
    public void afterRemovingE1SetDoesntContainsE1() throws Exception {
        testSet.remove("e1");
        assertTrue("afterRemovingE1SetDoesntContainsE1 test failed", !testSet.contains("e1"));
    }

}