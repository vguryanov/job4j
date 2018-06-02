package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by User2 on 02.06.2018.
 */
public class Board {
    private int size;
    private ReentrantLock[][] cells;

    public Board(int size) {
        this.size = size;
        cells = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new ReentrantLock();
            }
        }
    }

    public ReentrantLock get(int x, int y) {
        return cells[y][x];
    }

    public void lockCell(int x, int y){
        cells[y][x].lock();
    }

    public void unlockCell(int x, int y){
        cells[y][x].unlock();
    }

    public int getSize() {
        return size;
    }

    public void print() {
        for (ReentrantLock[] lockline : cells) {
            for (ReentrantLock lock : lockline) {
                System.out.print((lock.isLocked() ? "X" : ".") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
