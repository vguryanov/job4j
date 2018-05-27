package ru.job4j.list;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {
    protected int size;
    @GuardedBy("this")
    protected Node<E> first;
    protected int modCount;

    /**
     * Метод вставляет в начало списка данные.
     */
    public synchronized void add(E date) {
        Node<E> newNode = new Node<>(date);
        synchronized (newNode) {
            newNode.next = this.first;
        }
        this.first = newNode;
        this.size++;
        modCount++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public synchronized E delete() {
        Node<E> removedNode = this.first;
        this.first = removedNode.next;
        size--;
        modCount++;
        return removedNode.date;
    }

    public E removeLast() {
        modCount++;
        Node removedNode = getNode(getSize() - 1);
        if (size == 1) {
            synchronized (this) {
                first = null;
            }
        } else {
            getNode(getSize() - 2).next = null;
        }
        size--;
        return (E) removedNode.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        return getNode(index).date;
    }

    public synchronized Node<E> getNode(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public synchronized boolean contains(E e) {
        Node<E> cursor = first;
        while (cursor != null) {
            if (cursor.date.equals(e)) {
                return true;
            }
            cursor = cursor.next;
        }
        return false;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }


    public synchronized boolean hasCycle() {
        if (first == null) {
            return false;
        }

        Node slowCursor = first;
        Node fastCursor = first.next;
        while (fastCursor != null && slowCursor != null) {
            if (fastCursor == slowCursor) {
                return true;
            }
            slowCursor = slowCursor.next;
            fastCursor = fastCursor.next.next;
        }
        return false;
    }

    public synchronized boolean checkForCycleUsingSize() {
        Node cursor = first;
        for (int i = 0; i <= size; i++) {
            if (cursor == null) {
                return false;
            }
            cursor = cursor.next;
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private SimpleLinkedList.Node<E> currentNode;

            @Override
            public boolean hasNext() {
                return size > 0 && (currentNode == null || currentNode.next != null);
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

    /**
     * Класс предназначен для хранения данных.
     */
    protected static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}