package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User2 on 24.04.2018.
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();

        for (int[] row : array) {
            for (int i : row) {
                list.add(i);
            }
        }

        return list;
    }
}
