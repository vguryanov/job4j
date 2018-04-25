package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Created by User2 on 18.04.2018.
 */
public interface Input {
    String ask(String message);

    int ask(String question, ArrayList<Integer> range);
}
