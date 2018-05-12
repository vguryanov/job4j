package ru.job4j.list;

public class SimpleLinkedList<E> {
    protected int size;
    protected Node<E> first;
    protected int modCount;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newNode = new Node<>(date);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
        modCount++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        Node<E> removedNode = this.first;
        this.first = removedNode.next;
        size--;
        modCount++;
        return removedNode.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        return getNode(index).date;
    }

    public Node<E> getNode(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }


    public boolean hasCycle() {
        if (first == null) {
            return false;
        }

        Node slowCursor = first;
        Node fastCursor = first.next;
        while (fastCursor != null && slowCursor != null) {
            if (fastCursor == slowCursor) {
                return true;
            }
            slowCursor = slowCursor.next;
            fastCursor = fastCursor.next.next;
        }
        return false;
    }

    public boolean checkForCycleUsingSize() {
        Node cursor = first;
        for (int i = 0; i <= size; i++) {
            if (cursor == null) {
                return false;
            }
            cursor = cursor.next;
        }
        return true;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    protected static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}