package ru.job4j.bomberman;

/**
 * Created by User2 on 02.06.2018.
 */
public class Game {
    private final Board field;
    private int heroX = 0;
    private int heroY = 0;

    public Game(int fieldSize) {
        this.field = new Board(fieldSize);
    }

    public void start() {
        new HeroMotionThread().start();
    }

    private class HeroMotionThread extends Thread {
        @Override
        public void run() {
            field.lockCell(heroX, heroY);
            while (!isInterrupted()) {
                boolean isLockObtained = false;
                int startX = heroX, startY = heroY;
                while (!isLockObtained) {
                    makeRandomMove();
                    isLockObtained = field.tryLockCell(heroX, heroY);
                    if (!isLockObtained) {
                        heroX = startX;
                        heroY = startY;
                    }
                }
                field.unlockCell(startX, startY);
                field.print();
            }
        }

        private int getRandomVector() {
            return Math.random() > 0.5 ? -1 : 1;
        }

        private void makeRandomMove() {
            switch (getRandomVector()) {
                case -1:
                    heroX += getRandomVector();
                    if (heroX == field.getSize()) {
                        heroX -= 2;
                    }
                    if (heroX < 0) {
                        heroX += 2;
                    }
                    break;
                case 1:
                    heroY += getRandomVector();
                    if (heroY == field.getSize()) {
                        heroY -= 2;
                    }
                    if (heroY < 0) {
                        heroY += 2;
                    }
                    break;
            }
        }
    }
}
