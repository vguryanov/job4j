package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by User2 on 26.04.2018.
 */
public class SortUser {
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>();
        for (User user : users) {
            result.add(user);
        }

        return result;
    }
}
