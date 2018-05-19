package ru.job4j.tree;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    private Tree<Integer> tree;

    @Before
    public void setUp() throws Exception {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void sequentialIteration() {
        int expected = 1;
        for (Integer i : tree) {
            assertThat(i, is(expected++));
        }
    }

    @Test
    public void ifOneOfNodesHaveMoreThanTwoChildrenThenIsBinaryReturnsFalse() throws Exception {
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void ifNodesHaveLessThanThreeChildrenThenIsBinaryReturnsTrue() throws Exception {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }
}
