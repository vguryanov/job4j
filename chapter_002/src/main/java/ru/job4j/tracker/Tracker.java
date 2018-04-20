package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RNDM = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(System.currentTimeMillis() + RNDM.nextInt(50));
    }

    private int findIndexById(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    public Item findItemById(String id) {
        return items[this.findIndexById(id)];
    }

    public Item[] findByName(String key) {
        LinkedList<Item> resultList = new LinkedList();
        int itemCount = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getName().equals(key)) {
                resultList.add(items[i]);
                itemCount++;
            }
        }

        if (resultList.size() > 0) {
            return resultList.toArray(new Item[itemCount]);
        } else {
            return null;
        }
    }

    public Item[] getAll() {
        ArrayList<Item> itemList = new ArrayList<>();
        int itemCount = 0;

        for (Item item : items) {
            if (item != null) {
                itemList.add(item);
                itemCount++;
            }
        }

        if (itemCount > 0) {
            return itemList.toArray(new Item[itemCount]);
        }
        return null;
    }

    public void replace(String id, Item item) {
        item.setId(id);
        items[findIndexById(id)] = item;
    }

    public void delete(String id) {
        int deletedItemIndex = findIndexById(id);
        System.arraycopy(items, deletedItemIndex + 1, items, deletedItemIndex, items.length - 2);
        position--;
        System.out.println("Item " + id + " deleted");
    }

    public void showAllItems() {
        Item[] items = this.getAll();
        if (items != null) {
            for (Item item : this.getAll()) {
                System.out.println(item);
            }
        }
    }
}