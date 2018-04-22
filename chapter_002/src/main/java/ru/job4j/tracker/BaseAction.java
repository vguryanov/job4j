package ru.job4j.tracker;

/**
 * Created by User2 on 20.04.2018.
 */
abstract class BaseAction implements MenuAction {
    protected int key;
    private String info;

    public BaseAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    int getKey() {
        return key;
    }

    String info() {
        return info;
    }
}
