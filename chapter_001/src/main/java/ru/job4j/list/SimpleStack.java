package ru.job4j.list;

/**
 * Created by User2 on 12.05.2018.
 */
public class SimpleStack<T> {
    private LinkedListContainer<T> elementData = new LinkedListContainer<T>();

    public T poll() {
        return elementData.delete();
    }

    public void push(T value) {
        elementData.add(value);
    }
}
