package ru.job4j.java8;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by User2 on 12.08.2018.
 */
public class Students {
    public static Map<String, Double> getAvgGradesForDeps(Collection<Student> students) {
        return students.stream()
                .collect(
                        Collectors.groupingBy(
                                Student::getDepartment,
                                Collectors.averagingDouble(
                                        Student::getGrade
                                )
                        )
                );
    }
}
