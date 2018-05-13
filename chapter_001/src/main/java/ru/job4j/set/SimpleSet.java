package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.Iterator;

/**
 * Created by User2 on 14.05.2018.
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicArray<E> elementData = new DynamicArray<>();

    public boolean add(E e) {
        if (!this.contains(e)) {
            return elementData.add(e);
        }
        return false;
    }

    private boolean contains(E e) {
        for (E element : elementData) {
            if (element.equals(elementData)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return elementData.iterator();
    }
}
