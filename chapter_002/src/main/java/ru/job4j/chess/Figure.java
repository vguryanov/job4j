package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Created by User2 on 22.04.2018.
 */
public abstract class Figure {
    private CellCoordinate position;

    public void setPosition(CellCoordinate position) {
        this.position = position;
    }

    public CellCoordinate getPosition() {
        return position;
    }

    public abstract CellCoordinate[] getWay(CellCoordinate source, CellCoordinate dest) throws ImpossibleMoveException;
}
