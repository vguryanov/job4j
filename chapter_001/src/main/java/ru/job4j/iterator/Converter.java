package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by User2 on 09.05.2018.
 */
public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Iterator<Integer>> iterators = it;
            private Iterator<Integer> currentIterator = it.next();

            @Override
            public boolean hasNext() {
                if (!currentIterator.hasNext() && iterators.hasNext()) {
                    currentIterator = iterators.next();
                }
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                hasNext();
                return currentIterator.next();
            }
        };
    }
}