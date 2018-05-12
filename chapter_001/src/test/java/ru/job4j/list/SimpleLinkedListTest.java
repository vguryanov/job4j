package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> list;

    private void makeCycle() {
        list.getNode(1).next = list.getNode(0);
    }

    @Before
    public void setUp() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenInvokeDeletionTwiceOnlyThirdElementRemains() {
        list.delete();
        list.delete();
        assertThat(list.get(0), is(1));
        assertThat(list.getSize(), is(1));
    }

    @Test
    public void ifListContainsCycleHasCycleMethodReturnsTrue() throws Exception {
        makeCycle();
        assertTrue("test failed", list.hasCycle());
    }

    @Test
    public void ifListContainsCycleCycleCheckMethodReturnsTrue() throws Exception {
        makeCycle();
        assertTrue("test failed", list.checkForCycleUsingSize());
    }
}