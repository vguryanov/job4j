package ru.job4j.servlets.persistence;

import java.util.Map;

/**
 * Created by User2 on 12.07.2018.
 */
public interface Store<T> {
    public boolean add(String name, String login, String email);

    public boolean update(int id, String name);

    public boolean delete(int id);

    public boolean contains(int id);

    public T findById(int id);

    public Map<Integer, T> getAll();
}
