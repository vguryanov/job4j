package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

import java.util.LinkedList;

/**
 * Created by User2 on 22.04.2018.
 */
public class Bishop extends Figure {
    @Override
    public CellCoordinate[] getWay(CellCoordinate source, CellCoordinate dest) throws ImpossibleMoveException {
        LinkedList<CellCoordinate> result = new LinkedList<>();
        boolean isDestinationAchieved = false;

        int xDirection = Integer.compare(dest.getX(), source.getX());
        int yDirection = Integer.compare(dest.getY(), source.getY());

        for (int x = source.getX() + xDirection, y = source.getY() + yDirection;
             x >= 0 && y >= 0 && x < Board.getSIZE() && y < Board.getSIZE() && !isDestinationAchieved;
             x += xDirection, y += yDirection) {
            result.add(new CellCoordinate(x, y));
            if (x == dest.getX() && y == dest.getY()) {
                isDestinationAchieved = true;
            }
        }

        if (!isDestinationAchieved) {
            throw new ImpossibleMoveException();
        }
        return result.toArray(new CellCoordinate[result.size()]);
    }
}
