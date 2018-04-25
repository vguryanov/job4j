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
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */
    private static final Random RNDM = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
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
        return String.valueOf(RNDM.nextInt(10000));
    }

    private int findIndexById(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    public Item findItemById(String id) {
        return items.get(this.findIndexById(id));
    }

    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> resultList = new ArrayList<>();
        int itemCount = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getName().equals(key)) {
                resultList.add(items.get(i));
                itemCount++;
            }
        }

        if (resultList.size() > 0) {
            return resultList;
        } else {
            return null;
        }
    }

    public ArrayList<Item> getAll() {
        ArrayList<Item> itemList = new ArrayList<>();

        for (Item item : items) {
            if (item != null) {
                itemList.add(item);
            }
        }

        if (itemList.size() > 0) {
            return itemList;
        }
        return null;
    }

    public void replace(String id, Item item) {
        item.setId(id);
        items.set(findIndexById(id), item);
    }

    public void delete(String id) {
        int deletedItemIndex = findIndexById(id);
        items.remove(items.get(deletedItemIndex));
        System.out.println("Item " + id + " deleted");
    }

    public void showAllItems() {
        ArrayList<Item> items = this.getAll();
        if (items != null) {
            for (Item item : this.getAll()) {
                System.out.println(item);
            }
        }
    }
}