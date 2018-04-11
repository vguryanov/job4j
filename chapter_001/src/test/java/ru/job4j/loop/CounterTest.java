package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 11.04.2018.
 */
public class CounterTest {
    @Test
    public void add() throws Exception {
        int counterResult = new Counter().add(1, 10);
        assertTrue("Add() method test failed", counterResult == 30);
    }
}