package ru.job4j.wordindex;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by User2 on 25.05.2018.
 */
public class WordIndexTest {
    private WordIndex wordIndex;
    private static String testFilePath = "src\\test\\resources\\test.txt";
    private static String testFileContent = "﻿Несколько месяцев назад разработчики Chrome объявили, что в июле 2018 года начнут помечать "
            + "как небезопасные все страницы HTTP. \r\n"
            + "Значок «Не защищено» (“Not secure”) появится в адресной строке Chrome рядом с URL.";
    private static int[] testWordIndexes = new int[]{193, 38};

    @Before
    public void setUp() {
        wordIndex = new WordIndex(testFilePath);
    }

    @Test
    public void loadFile() throws Exception {
        String result = wordIndex.getFileContent();
        assertThat(result, is(testFileContent));
    }

    @Test
    public void getIndexes4Word() throws Exception {
        int count = 0;
        for (int i : wordIndex.getIndexes4Word("Chrome")) {
            assertThat(i, is(testWordIndexes[count++]));
        }
    }
}