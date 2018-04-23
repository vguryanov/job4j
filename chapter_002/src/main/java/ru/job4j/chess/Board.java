package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

import java.util.LinkedList;

/**
 * Created by User2 on 22.04.2018.
 */
public class Board {
    private static final int SIZE = 8;
    private static int[][] cells = new int[SIZE][SIZE];
    private static final LinkedList<Figure> PLACED_FIGURES = new LinkedList<>();

    private Board() {

    }

    public static int getSIZE() {
        return SIZE;
    }

    public static boolean isCellFree(CellCoordinate cellCoordinate) {
        return isCellFree(cellCoordinate.getX(), cellCoordinate.getY());
    }

    public static boolean isCellFree(int x, int y) {
        return cells[y][x] == 0;
    }

    private static void setCellsOccupied(boolean occupied, CellCoordinate... cells) {
        for (CellCoordinate cell : cells) {
            setCellOccupied(cell.getX(), cell.getY(), occupied);
        }
    }

    private static void setCellOccupied(int x, int y, boolean occupied) {
        cells[y][x] = occupied ? 1 : 0;
    }

    public static void placeNewFigure(Figure figure, CellCoordinate position) {
        if (!isCellFree(position)) {
            throw new IllegalArgumentException();
        }

        figure.setPosition(position);
        PLACED_FIGURES.add(figure);
        setCellsOccupied(true, figure.getPosition());
    }

    public static boolean moveFigure(CellCoordinate source, CellCoordinate dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure removingFigure = null;
        for (Figure figure : PLACED_FIGURES) {
            if (figure.getPosition().equals(source)) {
                removingFigure = figure;
            }
        }

        if (removingFigure == null) {
            throw new FigureNotFoundException();
        }

        CellCoordinate[] way = removingFigure.getWay(removingFigure.getPosition(), dest);

        if (!isWayClear(way)) {
            throw new OccupiedWayException();
        }

        setCellsOccupied(false, removingFigure.getPosition());
        removingFigure.setPosition(dest);
        setCellsOccupied(true, removingFigure.getPosition());

        return true;
    }

    private static boolean isWayClear(CellCoordinate[] way) {
        for (CellCoordinate cellCoordinate : way) {
            if (!isCellFree(cellCoordinate)) {
                return false;
            }
        }
        return true;
    }

    public static void print() {
        System.out.println("    0 1 2 3 4 5 6 7\n");

        for (int i = 0; i < SIZE; i++) {
            StringBuilder line = new StringBuilder(String.valueOf(i)).append("   ");
            for (int j = 0; j < SIZE; j++) {
                line.append(cells[i][j]).append(" ");
            }
            System.out.println(line);
        }
        System.out.println();
    }

    public static void clear() {
        cells = new int[SIZE][SIZE];
        PLACED_FIGURES.clear();
    }
}
