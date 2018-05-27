package ru.job4j.list;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 10.05.2018.
 */

@ThreadSafe
public class DynamicArray<E> implements Iterable<E> {
    @GuardedBy("this")
    private Object[] container = new Object[10];
    private int size = 0;
    private transient int modCount = 0;

    private synchronized void ensureCapacity() {
        if (size == container.length) {
            modCount++;
            container = Arrays.copyOf(container, size + size / 2);
        }
    }

    public synchronized boolean add(E e) {
        ensureCapacity();
        container[size++] = e;
        return true;
    }

    public synchronized E get(int index) {
        return (E) container[index];
    }

    public int size() {
        return size;
    }

    public synchronized boolean contains(E e) {
        for (int i = 0; i < size(); i++) {
            if (container[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public synchronized E delete(int index) {
        modCount++;
        E oldValue = (E) container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return oldValue;
    }

    public E delete(E e) throws NoSuchElementException {
        return delete(getIndexOf(e));
    }

    private synchronized int getIndexOf(E e) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (e.equals(this.container[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public synchronized String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (container[i] != null) {
                result.append(" [" + container[i].toString() + "] ");
            }
        }

        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                synchronized (DynamicArray.this) {
                    checkForComodification();
                    int i = cursor;
                    if (i >= size) {
                        throw new NoSuchElementException();
                    }
                    Object[] elementData = DynamicArray.this.container;
                    if (i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    cursor = i + 1;
                    return (E) elementData[i];
                }
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
