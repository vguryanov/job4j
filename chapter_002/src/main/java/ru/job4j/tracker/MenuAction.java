package ru.job4j.tracker;

/**
 * Created by User2 on 20.04.2018.
 */
public interface MenuAction {
    int getKey();

    void execute();

    String info();
}
