package ru.job4j.departments;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by User2 on 06.05.2018.
 */
public class DepartmentsSet extends TreeSet<String> {
    @Override
    public boolean add(String s) {
        String[] levels = s.split("\\\\");
        String tempString = "";
        for (int i = 0; i < levels.length - 1; i++) {
            tempString += levels[i];
            super.add(tempString);
            tempString += "\\";
        }
        return super.add(s);
    }
}
