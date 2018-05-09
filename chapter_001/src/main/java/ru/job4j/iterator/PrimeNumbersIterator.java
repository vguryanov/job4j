package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 09.05.2018.
 */
public class PrimeNumbersIterator implements Iterator<Integer> {
    private final int[] array;
    private int index = -1;
    private int nextPrimeNumberIndex = -1;

    public PrimeNumbersIterator(int[] array) {
        this.array = array;
    }

    private int findNextPrimeNumberIndex() {
        for (int i = index + 1; i < array.length; i++) {
            if (isNumberPrime(array[i])) {
                nextPrimeNumberIndex = i;
                return nextPrimeNumberIndex;
            }
        }
        return -1;
    }

    private boolean isNumberPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        return findNextPrimeNumberIndex() != -1;
    }

    @Override
    public Integer next() {
        if (nextPrimeNumberIndex == -1) {
            if (findNextPrimeNumberIndex() == -1) {
                throw new NoSuchElementException();
            }
        }
        index = nextPrimeNumberIndex;
        nextPrimeNumberIndex = -1;
        return array[index];
    }
}
