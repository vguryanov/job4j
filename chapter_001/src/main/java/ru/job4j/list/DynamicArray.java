package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 10.05.2018.
 */
public class DynamicArray<E> implements Iterable<E> {
    private Object[] container = new Object[10];
    private int size = 0;
    protected transient int modCount = 0;

    private void ensureCapacity() {
        if (size == container.length) {
            modCount++;
            container = Arrays.copyOf(container, size + size / 2);
        }
    }

    public boolean add(E e) {
        ensureCapacity();
        container[size++] = e;
        return true;
    }

    public E get(int index) {
        return (E) container[index];
    }

    public int size() {
        return size;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size(); i++) {
            if (container[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E delete(int index) {
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

    private int getIndexOf(E e) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (e.equals(this.container[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
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

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
