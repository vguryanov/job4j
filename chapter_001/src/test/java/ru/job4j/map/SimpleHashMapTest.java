package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 18.05.2018.
 */
public class SimpleHashMapTest {
    private SimpleHashMap<String, Integer> testMap;

    private void addTestEntries(int amount) {
        for (int i = 0; i < amount; i++) {
            testMap.insert("key " + Math.random(), null);
        }
    }

    private int getInnerCapacity() throws Exception {
        Field hashTableCapacityField = testMap.getClass().getDeclaredField("hashTableCapacity");
        hashTableCapacityField.setAccessible(true);
        int hashTableCapacity = (Integer) hashTableCapacityField.get(testMap);
        return hashTableCapacity;
    }

    @Before
    public void setUp() {
        testMap = new SimpleHashMap<>();
    }

    @Test
    public void afterAddingOneThousandElementsCapacityGrows() throws Exception {
        int initialTestMapCapacity = getInnerCapacity();
        addTestEntries(1000);
        int currentTestMapCapacity = getInnerCapacity();
        assertTrue("afterAddingOneThousandElementsCapacityGrows test failed",
                currentTestMapCapacity > initialTestMapCapacity);
    }

    @Test
    public void afterInsertingKeyAndOneGetMethodReturnsOneByKey() throws Exception {
        String key = "key";
        testMap.insert(key, 1);
        int result = testMap.get(key);
        assertThat(result, is(1));
    }

    @Test
    public void afterDeletingByKeyFromMapGetSizeReturnsZero() throws Exception {
        String key = "key";
        testMap.insert(key, 1);
        testMap.delete(key);
        int result = testMap.getSize();
        assertThat(result, is(0));
    }

    @Test
    public void iterationCountMustEqualMapSize() {
        addTestEntries(20);
        int iterationCounter = 0;
        for (SimpleHashMap.Entry<String, Integer> e : testMap) {
            iterationCounter++;
        }
        assertThat(iterationCounter, is(testMap.getSize()));
    }
}