package ru.job4j.chess;

import org.junit.After;
import org.junit.Test;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

import static org.junit.Assert.*;

/**
 * Created by User2 on 22.04.2018.
 */
public class BoardTest {
    @After
    public void clearBoard() {
        Board.clear();
    }

    @Test
    public void moveFigure() throws Exception {
        Bishop bishop = new Bishop();
        CellCoordinate startPositionForBishop = new CellCoordinate(0, 0);
        Board.placeNewFigure(bishop, startPositionForBishop);

        CellCoordinate destination = new CellCoordinate(7, 7);
        assertTrue("Move failed", Board.moveFigure(bishop.getPosition(), destination));
        assertTrue("Cell is not cleared", Board.isCellFree(startPositionForBishop));
        assertTrue("Cell is not occupied", !Board.isCellFree(destination));
    }

    @Test(expected = FigureNotFoundException.class)
    public void ifCellHasNoFigureThenException() throws Exception {
        Board.moveFigure(new CellCoordinate(1, 1), new CellCoordinate(3, 6));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void ifMoveIsImpossibleThenException() throws Exception {
        Bishop bishop = new Bishop();
        Board.placeNewFigure(bishop, new CellCoordinate(0, 0));

        Board.moveFigure(bishop.getPosition(), new CellCoordinate(3, 6));
    }

    @Test(expected = OccupiedWayException.class)
    public void ifWayIsOccupiedThenException() throws Exception {
        Bishop bishop = new Bishop();
        Board.placeNewFigure(bishop, new CellCoordinate(0, 0));

        Bishop bishop2 = new Bishop();
        Board.placeNewFigure(bishop2, new CellCoordinate(3, 3));

        Board.moveFigure(bishop.getPosition(), new CellCoordinate(6, 6));
    }
}