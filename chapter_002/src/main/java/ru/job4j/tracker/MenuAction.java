package ru.job4j.tracker;

/**
 * Created by User2 on 20.04.2018.
 */
abstract class MenuAction {
    protected int key;
    private String info;

    public MenuAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    int getKey() {
        return key;
    }

    abstract void execute();

    String info() {
        return info;
    }
}
