package ru.job4j.bomberman;

/**
 * Created by User2 on 02.06.2018.
 */
public class Game {
    private final Board field;

    public Game(int fieldSize) {
        this.field = new Board(fieldSize);
    }

    public void start() {
        field.start();
    }
}
