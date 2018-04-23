package ru.job4j.chess;

/**
 * Created by User2 on 22.04.2018.
 */
public class CellCoordinate {
    private int x, y;

    public CellCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CellCoordinate) {
            CellCoordinate o = (CellCoordinate) obj;
            return this.x == o.getX() && this.y == o.getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }
}
