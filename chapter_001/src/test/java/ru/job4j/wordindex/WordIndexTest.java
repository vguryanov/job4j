package ru.job4j.wordindex;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by User2 on 25.05.2018.
 */
public class WordIndexTest {
    private WordIndex wordIndex;
    private String testFileContent = "Несколько месяцев назад разработчики Chrome объявили, что в июле 2018 года начнут помечать "
            + "как небезопасные все страницы HTTP.   "
            + "Значок «Не защищено» (“Not secure”) появится в адресной строке Chrome рядом с URL.";
    private int[] testWordIndexes = new int[]{192, 37};

    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.txt").getFile());
        wordIndex = new WordIndex(file);
    }

    @Test
    public void loadFile() throws Exception {
        String result = wordIndex.getFileContent();
        assertThat(result, is(testFileContent));
    }

    @Test
    public void getIndexes4Word() throws Exception {
        for (int i = 0; i < testFileContent.length(); i++) {
            System.out.printf("%i: &s &s", i, testFileContent.charAt(i), wordIndex.getFileContent().charAt(i));
        }

        int count = 0;
        for (int i : wordIndex.getIndexes4Word("Chrome")) {
            assertThat(i, is(testWordIndexes[count++]));
        }
    }
}