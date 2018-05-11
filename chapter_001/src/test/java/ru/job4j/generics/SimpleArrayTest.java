package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 11.05.2018.
 */
public class SimpleArrayTest {
    private SimpleArray<String> testSimpleArray;
    int testCapacity = 3;

    private void fillTestSimpleArray(int elementsAmount) {
        for (int i = 0; i < elementsAmount; i++) {
            testSimpleArray.add("e" + i);
        }
    }

    @Before
    public void setUp() throws Exception {
        testSimpleArray = new SimpleArray<>(testCapacity);
        fillTestSimpleArray(testCapacity);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddingElementAboveCapacityValueThrowsAIOBException() throws Exception {
        testSimpleArray.add("");
    }

    @Test
    public void whenRemoveSecondElementFirstAndThirdRemains() throws Exception {
        testSimpleArray.delete(1);
        String result = testSimpleArray.toString();
        String expected = "[e0] [e2]";
        assertThat(result, is(expected));
    }

    @Test
    public void whenSetFirstElementToThreeGetMethodReturnsThreeForFirstElement() {
        testSimpleArray.set(0, "Three");
        assertThat(testSimpleArray.get(0), is("Three"));
    }
}