package ru.job4j.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by User2 on 19.05.2018.
 */
public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> root;
    private int size;

    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size++;
            return true;
        }
        return addLeaf(e);
    }

    private boolean addLeaf(E e) {
        boolean result = root.addLeaf(e);
        if (result) {
            size++;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Queue<Node<E>> nodeQueue = new LinkedList<>();

            {
                nodeQueue.offer(BinaryTree.this.root);
            }

            @Override
            public boolean hasNext() {
                return !nodeQueue.isEmpty();
            }

            @Override
            public E next() {
                Node<E> next = nodeQueue.poll();
                Node<E> nextLeft = next.getLeft();
                Node<E> nextRight = next.getRight();
                if (nextLeft != null) {
                    nodeQueue.offer(nextLeft);
                }
                if (nextRight != null) {
                    nodeQueue.offer(nextRight);
                }
                return next.getValue();
            }
        };
    }

    public static class Node<E extends Comparable<E>> {
        private final E value;
        private BranchContainer left = new BranchContainer();
        private BranchContainer right = new BranchContainer();

        public Node(final E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        Node<E> getLeft() {
            return left.getBranch();
        }

        Node<E> getRight() {
            return right.getBranch();
        }

        private boolean addLeaf(E e) {
            int comparisonIndex = e.compareTo(value);
            if (comparisonIndex == 0) {
                return false;
            }
            BranchContainer nextBranch = comparisonIndex < 0 ? left : right;
            return nextBranch.addLeaf(e);
        }

        @Override
        public String toString() {
            return value.toString();
        }

        private class BranchContainer {
            private Node<E> branch;

            private Node<E> getBranch() {
                return branch;
            }

            private boolean addLeaf(E e) {
                if (branch == null) {
                    branch = new Node<>(e);
                    return true;
                }
                return branch.addLeaf(e);
            }

            @Override
            public String toString() {
                return branch.toString();
            }
        }
    }
}
