package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 12.05.2018.
 */
public class LinkedListContainer<E> extends SimpleLinkedList<E> implements Iterable<E> {
    private int modCount;

    @Override
    public void add(E date) {
        super.add(date);
        modCount++;
    }

    @Override
    public E delete() {
        modCount++;
        return super.delete();
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
