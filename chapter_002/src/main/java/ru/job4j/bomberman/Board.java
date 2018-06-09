package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by User2 on 02.06.2018.
 */
public class Board {
    private int size;
    private final ReentrantLock[][] cells;
    private int heroX = 0;
    private int heroY = 0;
    private HeroMotionThread hero = new HeroMotionThread();

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

    public void lockCell(int x, int y) {
        cells[y][x].lock();
    }

    public boolean tryLockCell(int x, int y) {
        try {
            return cells[y][x].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void unlockCell(int x, int y) {
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

    public void start() {
        hero.start();
    }

    public void interrupt() {
        hero.interrupt();
    }

    private class HeroMotionThread extends Thread {
        private boolean isNew = true;

        @Override
        public void run() {
            while (!isInterrupted()) {
                if (isNew) {
                    Board.this.lockCell(heroX, heroY);
                    isNew = false;
                }
                move();
            }
        }

        private void move() {
            boolean isLockObtained = false;
            int startX = heroX, startY = heroY;
            while (!isLockObtained) {
                makeRandomMove();
                isLockObtained = Board.this.tryLockCell(heroX, heroY);
                if (!isLockObtained) {
                    heroX = startX;
                    heroY = startY;
                }
            }
            Board.this.unlockCell(startX, startY);
            Board.this.print();
        }

        private int getRandomVector() {
            return Math.random() > 0.5 ? -1 : 1;
        }

        private void makeRandomMove() {
            switch (getRandomVector()) {
                case -1:
                    heroX += getRandomVector();
                    if (heroX == size) {
                        heroX -= 2;
                    }
                    if (heroX < 0) {
                        heroX += 2;
                    }
                    break;
                case 1:
                    heroY += getRandomVector();
                    if (heroY == size) {
                        heroY -= 2;
                    }
                    if (heroY < 0) {
                        heroY += 2;
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
