package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        String[] array = new String[]{"Мир", "Супер", "Привет", "Мир", "Привет", "Квадрат", "Супер", "Мир", "Супер", "Пока"};
        String[] expected = new String[]{"Квадрат", "Пока", "Мир", "Супер", "Привет"};
        String[] result = new ArrayDuplicate().remove(array);
        assertThat(result, is(expected));
    }
}