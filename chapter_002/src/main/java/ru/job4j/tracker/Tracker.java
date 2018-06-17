package ru.job4j.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements AutoCloseable {
    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public void add(Item item) {
        DBUtils.addItem(item);
    }

    public Item findItemById(String id) {
        Item result = null;
        try {
            result = DBUtils.getItemById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Item findByName(String name) {
        Item result = null;
        try {
            result = DBUtils.getItemByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void replace(String id, Item item) {
        DBUtils.replaceItemById(id, item);
    }

    public void delete(String id) {
        DBUtils.removeItem(id);
        System.out.println("Item " + id + " deleted");
    }

    public void showAllItems() {
        try {
            ResultSet items = DBUtils.getAllItems();
            while (items.next()) {
                if (items.getBoolean(6)) {
                    continue;
                }
                System.out.printf("%d %s %s %s %s \n",
                        items.getInt(1),
                        items.getString(2),
                        items.getString(3),
                        items.getString(4),
                        items.getTimestamp(5)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        DBUtils.closeConnection();
    }
}