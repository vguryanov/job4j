package ru.job4j.departments;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by User2 on 06.05.2018.
 */
public class DepartmentsSet extends TreeSet<String> {
    public DepartmentsSet() {
        super();
    }

    public DepartmentsSet(Comparator<? super String> comparator) {
        super(comparator);
    }

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

    @Override
    public DepartmentsSet descendingSet() {
        DepartmentsSet resultSet = new DepartmentsSet(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] o1Levels = o1.split("\\\\");
                String[] o2Levels = o2.split("\\\\");

                for (int i = 0; i < o1Levels.length && i < o2Levels.length; i++) {
                    int result = o2Levels[i].compareTo(o1Levels[i]);
                    if (result != 0) {
                        return result;
                    }
                }
                return o1.compareTo(o2);
            }
        });
        resultSet.addAll(this);
        return resultSet;
    }
}
