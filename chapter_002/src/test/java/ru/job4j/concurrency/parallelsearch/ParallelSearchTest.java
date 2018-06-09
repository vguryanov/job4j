package ru.job4j.concurrency.parallelsearch;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 01.06.2018.
 */
public class ParallelSearchTest {
    @Test
    public void init() throws Exception {
        ParallelSearch ps = new ParallelSearch(
                "",
                "test",
                Arrays.asList(".txt")
        );
        ps.init();
//        List<String> expected = Arrays.asList("src\\test\\resources\\testfile.txt", "target\\test-classes\\testfile.txt");
        List<String> expected = Arrays.asList(
                "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testfile.txt",
                "target" + File.separator + "test-classes" + File.separator + "testfile.txt");
        assertThat(ps.result(), is(expected));
    }
}