package ru.job4j.set;

import ru.job4j.list.DynamicArray;

/**
 * Created by User2 on 15.05.2018.
 */
public class SimpleHashSet<E> {
    private int tableCapacity = 10;
    private DynamicArray<E>[] table = new DynamicArray[this.tableCapacity];

    public SimpleHashSet() {
        for (int i = 0; i < this.tableCapacity; i++) {
            this.table[i] = new DynamicArray<E>();
        }
    }

    public boolean add(E e) {
        if (this.contains(e)) {
            return false;
        }
        return table[this.getHashIndexFor(e)].add(e);
    }

    public boolean contains(E e) {
        return this.table[this.getHashIndexFor(e)].contains(e);
    }

    public boolean remove(E e) {
        this.table[this.getHashIndexFor(e)].delete(e);
        return true;
    }

    private int getHashIndexFor(E e) {
        return Math.abs(e.hashCode() % tableCapacity);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tableCapacity; i++) {
            if (table[i].size() > 0) {
                result.append(table[i].toString());
            }
        }
        return result.toString();
    }
}
