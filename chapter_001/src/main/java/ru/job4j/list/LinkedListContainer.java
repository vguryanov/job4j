package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 12.05.2018.
 */
public class LinkedListContainer<E> extends SimpleLinkedList<E> implements Iterable<E> {
    @Override
    public void add(E date) {
        super.add(date);
    }

    public E removeLast() {
        modCount++;
        Node removedNode = getNode(getSize() - 1);
        if (size == 1) {
            first = null;
        } else {
            getNode(getSize() - 2).next = null;
        }
        size--;
        return (E) removedNode.date;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private SimpleLinkedList.Node<E> currentNode;

            @Override
            public boolean hasNext() {
                return currentNode == null || currentNode.next != null;
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (currentNode == null) {
                    currentNode = first;
                } else {
                    currentNode = currentNode.next;
                }
                return currentNode.date;
            }
        };
    }
}
