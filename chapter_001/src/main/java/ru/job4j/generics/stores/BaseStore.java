package ru.job4j.generics.stores;

import ru.job4j.generics.SimpleArray;
import ru.job4j.generics.Store;
import ru.job4j.generics.baseclasses.BaseClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User2 on 09.05.2018.
 */
public class BaseStore<T extends BaseClass> implements Store<T> {
    private SimpleArray<T> objects;

    public BaseStore(int capacity) {
        this.objects = new SimpleArray<T>(capacity);
    }

    @Override
    public void add(T model) {
        objects.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        try {
            objects.set(Integer.parseInt(id), model);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            objects.delete(Integer.parseInt(id));
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public T findById(String id) {
        try {
            return objects.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
