package ru.job4j.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

/**
 * Created by User2 on 12.08.2018.
 */
public class StudentsTest {
    @Test
    public void getAvgGradesForDeps() throws Exception {
        List<Student> students = Arrays.asList(
                new Student("John", 50, "CS", Student.Gender.MALE),
                new Student("Corn", 70, "Math", Student.Gender.MALE),
                new Student("Nick", 80, "CS", Student.Gender.FEMALE),
                new Student("Susan", 90, "Math", Student.Gender.FEMALE)
        );
        Map<String, Double> expected = new HashMap<>();
        expected.put("CS", 65.0);
        expected.put("Math", 80.0);
        Map<String, Double> result = Students.getAvgGradesForDeps(students);

        assertThat(result, is(expected));
    }
}