package ru.job4j.generics.baseclasses;

/**
 * Created by User2 on 09.05.2018.
 */
public class BaseClass {
    private final String id;

    protected BaseClass(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}