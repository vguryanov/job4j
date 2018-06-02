package ru.job4j.cache;

/**
 * Created by User2 on 01.06.2018.
 */
public class Base {
    private static int idCounter;
    private int id;
    private int version;
    private String name;

    public Base(String name) {
        this.id = ++idCounter;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    private void updateVersion() {
        version++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals(this.name)) {
            return;
        }
        this.name = name;
        updateVersion();
    }
}
