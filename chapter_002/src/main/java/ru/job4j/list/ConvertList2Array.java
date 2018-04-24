package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User2 on 24.04.2018.
 */
public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows + (list.size() % rows > 0 ? 1 : 0);
        int[][] result = new int[rows][cells];

        for (int i = 0, listIndex = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                int value = listIndex < list.size() ? list.get(listIndex) : 0;
                result[i][j] = value;
                listIndex++;
            }
        }

        return result;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();

        for (int[] array : list) {
            for (int i = 0; i < array.length; i++) {
                result.add(array[i]);
            }
        }

        return result;
    }
}
