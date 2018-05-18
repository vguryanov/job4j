package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by User2 on 18.05.2018.
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    private int hashTableCapacity = 50;
    private Entry<K, V>[] hashTable = new Entry[hashTableCapacity];
    private int size;

    public boolean insert(K key, V value) {
        int hashIndex = hash(key);
        ensureCapacity();
        if (hashTable[hashIndex] != null) {
            return false;
        }
        hashTable[hashIndex] = new Entry<K, V>(key, value);
        size++;
        return true;
    }

    public V get(K key) {
        Entry<K, V> resultEntry = hashTable[hash(key)];
        return resultEntry == null ? null : resultEntry.getValue();
    }

    public boolean delete(K key) {
        int hashIndex = hash(key);
        if (hashTable[hashIndex] == null) {
            return false;
        }
        hashTable[hashIndex] = null;
        size--;
        return true;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % hashTableCapacity);
    }

    private void ensureCapacity() {
        if (size == hashTableCapacity) {
            grow();
        }
    }

    private void grow() {
        hashTableCapacity *= 2;
        size = 0;
        Entry<K, V>[] sourceHashTable = hashTable;
        hashTable = new Entry[hashTableCapacity];

        for (Entry<K, V> entry : sourceHashTable) {
            if (entry != null) {
                this.insert(entry.getKey(), entry.getValue());
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        size = 0;
        hashTable = new Entry[hashTableCapacity];
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            private int cursor = -1;
            private int nextIndex = -1;

            @Override
            public boolean hasNext() {
                if (nextIndex > -1) {
                    return true;
                }
                for (int i = cursor == -1 ? 0 : cursor; i < hashTableCapacity; i++) {
                    if (hashTable[i] != null) {
                        nextIndex = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                cursor = nextIndex;
                nextIndex = -1;
                return hashTable[cursor++];
            }
        };
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
