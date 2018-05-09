package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 09.05.2018.
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] array;
    private int index = -1;
    private int nextEvenNumberIndex = -1;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    private int findNextEvenNumberIndex() {
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                nextEvenNumberIndex = i;
                return nextEvenNumberIndex;
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return findNextEvenNumberIndex() != -1;
    }

    @Override
    public Integer next() {
        if (nextEvenNumberIndex == -1) {
            if (findNextEvenNumberIndex() == -1) {
                throw new NoSuchElementException();
            }
        }
        index = nextEvenNumberIndex;
        nextEvenNumberIndex = -1;
        return array[index];
    }
}
