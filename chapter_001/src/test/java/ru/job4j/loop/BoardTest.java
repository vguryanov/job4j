package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String lineSeparator = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x%s", lineSeparator, lineSeparator, lineSeparator);

        assertThat(result, is(expected));
    }

    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        //напишите здесь тест, проверяющий формирование доски 5 на 4.
        Board board = new Board();
        String result = board.paint(5, 4);
        final String lineSeparator = System.getProperty("line.separator");
        String expected = String.format("x x x%s x x %sx x x%s x x %s",
                lineSeparator, lineSeparator, lineSeparator, lineSeparator);

        assertThat(result, is(expected));
    }
}