package ru.job4j.wordindex;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by User2 on 25.05.2018.
 */
public class IntegerTrieSet implements Set<Integer> {
    private PatriciaTrie<Integer> trie = new PatriciaTrie<>();

    @Override
    public int size() {
        return trie.size();
    }

    @Override
    public boolean isEmpty() {
        return trie.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return trie.containsKey(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private OrderedMapIterator<String, Integer> iterator = trie.mapIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return Integer.parseInt(iterator.next());
            }
        };
    }

    @Override
    public Object[] toArray() {
        return trie.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return trie.keySet().toArray(a);
    }

    @Override
    public boolean add(Integer integer) {
        trie.put(String.valueOf(integer), integer);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        trie.remove(o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return trie.keySet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        for (Object o : c) {
            this.add((Integer) o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Set<String> toRemove = new HashSet<>();
        for (String s : trie.keySet()) {
            if (!c.contains(Integer.parseInt(s))) {
                toRemove.add(s);
            }
        }
        for (String s : toRemove) {
            trie.remove(s);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            trie.remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        trie.clear();
    }
}
