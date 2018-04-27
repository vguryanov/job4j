package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by User2 on 26.04.2018.
 */
public class SortUser {
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                Integer o1NameLength = o1.getName().length();
                Integer o2NameLength = o2.getName().length();
                return o1NameLength.compareTo(o2NameLength);
            }
        });

        return users;
    }

    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result == 0 ? o1.getAge().compareTo(o2.getAge()) : result;
            }
        });

        return users;
    }
}
