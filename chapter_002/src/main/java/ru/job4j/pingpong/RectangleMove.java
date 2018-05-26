package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private Direction direction = Direction.RIGHT;

    private enum Direction {
        LEFT, RIGHT
    }

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (true) {
            move();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        checkForBorders();
        switch (direction) {
            case LEFT:
                this.rect.setX(this.rect.getX() - 1);
                break;
            case RIGHT:
                this.rect.setX(this.rect.getX() + 1);
                break;
            default:
                break;
        }
    }

    private void checkForBorders() {
        if (this.rect.getX() == 300) {
            direction = Direction.LEFT;
        } else if (this.rect.getX() == 0) {
            direction = Direction.RIGHT;
        }
    }
}