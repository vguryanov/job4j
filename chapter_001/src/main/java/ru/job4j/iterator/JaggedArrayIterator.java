package ru.job4j.iterator;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by User2 on 09.05.2018.
 */
public class JaggedArrayIterator<T> implements Iterator<T> {
    private final T[][] array;
    private int columnIndex = -1;
    private int lineIndex = 0;

    public JaggedArrayIterator(T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return lineIndex == array.length - 1 ? columnIndex < array[array.length - 1].length - 1 : lineIndex < array.length - 1;
    }

    @Override
    public T next() {
        if (columnIndex < array[lineIndex].length - 1) {
            columnIndex++;
        } else {
            lineIndex++;
            columnIndex = 0;
        }
        return array[lineIndex][columnIndex];
    }
}
