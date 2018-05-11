package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 09.05.2018.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] elementData;
    private int cursor = 0;

    public SimpleArray(int capacity) {
        this.elementData = new Object[capacity];
    }

    public boolean add(T model) {
        elementData[cursor++] = model;
        return true;
    }

    public T set(int index, T model) {
        T oldValue = (T) elementData[index];
        elementData[index] = model;
        return oldValue;
    }

    public T delete(int index) {
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - index - 1);
        elementData[elementData.length - 1] = null;
        return oldValue;
    }

    public T get(int index) {
        return (T) elementData[index];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null) {
                continue;
            }
            result.append(String.format("[%s] ", elementData[i].toString()));
        }
        return result.toString().trim();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = -1;

            @Override
            public boolean hasNext() {
                return cursor < elementData.length - 1;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elementData[++cursor];
            }
        };
    }
}
