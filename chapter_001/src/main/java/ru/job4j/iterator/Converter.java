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
                if (!currentIterator.hasNext()) {
                    currentIterator = iterators.next();
                }
                return currentIterator.next();
            }
        };
    }

    public static void main(String[] args) {
        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> it2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> it3 = Arrays.asList(7, 8, 9).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        Iterator<Integer> it = new Converter().convert(its);

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}