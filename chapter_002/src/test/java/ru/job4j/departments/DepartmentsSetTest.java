package ru.job4j.departments;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 06.05.2018.
 */
public class DepartmentsSetTest {
    @Test
    public void addingAndSorting() {
        DepartmentsSet set = new DepartmentsSet();
        set.addAll(Arrays.asList(
                "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2\\SK1",
                "K1\\SK2",
                "K1\\SK1",
                "K2\\SK1\\SSK2",
                "K1\\SK1\\SSK1"));
        String result = set.toString();
        String expected = "[K1, " +
                "K1\\SK1, " +
                "K1\\SK1\\SSK1, " +
                "K1\\SK1\\SSK2, " +
                "K1\\SK2, " +
                "K2, " +
                "K2\\SK1, " +
                "K2\\SK1\\SSK1, " +
                "K2\\SK1\\SSK2]";
        assertThat(result, is(expected));
    }
}