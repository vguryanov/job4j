package ru.job4j.set;

import ru.job4j.list.LinkedListContainer;

import java.util.Iterator;

/**
 * Created by User2 on 14.05.2018.
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    private LinkedListContainer<E> elementData = new LinkedListContainer<>();

    public void add(E e) {
        if (!this.contains(e)) {
            elementData.add(e);
        }
    }

    public boolean contains(E e) {
        return elementData.contains(e);
    }

    @Override
    public Iterator<E> iterator() {
        return elementData.iterator();
    }
}
