package ru.job4j.pseudo;

/**
 * Created by User2 on 19.04.2018.
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        return new StringBuilder()
                .append("  +  ")
                .append(" +++ ")
                .append("+++++")
                .toString();
    }
}
