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
        boolean isDirectionFound = false;

        int[][] directions = new int[][]{
                {1, 1},
                {-1, 1},
                {-1, -1},
                {1, -1}
        };

        for (int i = 0; i < directions.length && !isDirectionFound; i++) {
            for (int x = source.getX(), y = source.getY(); x >= 0 && y >= 0 && x < Board.getSIZE() && y < Board.getSIZE(); x += directions[i][0], y += directions[i][1]) {
                if (isDirectionFound) {
                    result.add(new CellCoordinate(x, y));
                    if (x == dest.getX() && y == dest.getY()) {
                        break;
                    }
                } else if (dest.getX() == x && dest.getY() == y) {
                    isDirectionFound = true;
                    x = source.getX();
                    y = source.getY();
                }
            }
        }

        if (!isDirectionFound) {
            throw new ImpossibleMoveException();
        }
        return result.toArray(new CellCoordinate[result.size()]);
    }
}
