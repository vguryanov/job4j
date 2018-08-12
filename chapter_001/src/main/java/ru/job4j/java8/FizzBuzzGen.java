package ru.job4j.java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by User2 on 12.08.2018.
 */
public class FizzBuzzGen {
    public static List<String> generate() {
        return Stream.iterate(1, i -> i + 1).limit(100)
                .map(i -> new Object() {
                    String value = String.valueOf(i);
                    String result = String.valueOf(i % 3).replace("0", "Fizz")
                            + String.valueOf(i % 5).replace("0", "Buzz");
                })
                .map(o -> o.result.replaceAll("(Fizz)\\d+", "Fizz")
                        .replaceAll("\\d+(Buzz)", "Buzz")
                        .replaceAll("\\d+", o.value))
                .collect(Collectors.toList());
    }
}
