package ru.job4j.generics;

import ru.job4j.generics.baseclasses.BaseClass;

/**
 * Created by User2 on 09.05.2018.
 */
public interface Store<T extends BaseClass> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
