package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 03.06.2018.
 */
public class GameTest {
    @Test
    public void start() throws Exception {
        new Game(10).start();
        Thread.sleep(5000);
    }
}