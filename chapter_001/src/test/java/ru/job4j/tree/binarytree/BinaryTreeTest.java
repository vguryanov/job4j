package ru.job4j.tree.binarytree;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 20.05.2018.
 */
public class BinaryTreeTest {
    private BinaryTree<Integer> testTree;
    private Set<Integer> valueSet;

    private BinaryTree.Node<Integer> getRootNode() throws Exception {
        Field rootField = testTree.getClass().getDeclaredField("root");
        rootField.setAccessible(true);
        return (BinaryTree.Node<Integer>) rootField.get(testTree);
    }

    private boolean treeIsValid() throws Exception {
        return nodeIsValid(getRootNode());
    }

    private boolean nodeIsValid(BinaryTree.Node<Integer> node) {
        boolean result = true;
        BinaryTree.Node<Integer> left = node.getLeft();
        BinaryTree.Node<Integer> right = node.getRight();
        if (left != null) {
            if (left.getValue() > node.getValue()) {
                return false;
            }
            result = nodeIsValid(left);
        }
        if (right != null) {
            if (right.getValue() < node.getValue()) {
                return false;
            }
            result = nodeIsValid(right);
        }
        return result;
    }

    @Before
    public void setUp() throws Exception {
        testTree = new BinaryTree<>();
        valueSet = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            int value = (int) (Math.random() * 100);
            testTree.add(value);
            valueSet.add(value);
        }
    }

    @Test
    public void sequentialBinaryTreeNodeValidation() throws Exception {
        assertTrue("sequentialBinaryTreeNodeValidation failed", treeIsValid());
    }

    @Test
    public void iteratorsValuesMustMatchTreeValues() throws Exception {
        Set<Integer> resultSet = new HashSet<>();
        for (Integer i : testTree) {
            resultSet.add(i);
        }
        assertThat(resultSet, is(valueSet));
    }
}