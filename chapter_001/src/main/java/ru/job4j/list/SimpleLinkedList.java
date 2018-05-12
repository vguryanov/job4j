package ru.job4j.list;

public class SimpleLinkedList<E> {
    protected int size;
    protected Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newNode = new Node<>(date);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        Node<E> removedNode = this.first;
        this.first = removedNode.next;
        size--;
        return removedNode.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
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