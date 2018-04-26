package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 26.04.2018.
 */
public class SortUserTest {
    @Test
    public void sort() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("name1", 43));
        users.add(new User("name2", 22));
        users.add(new User("name3", 31));

        Set<User> resultUserSet = new SortUser().sort(users);
        StringBuilder result = new StringBuilder();
        for (User user : resultUserSet) {
            result.append(user);
        }
        String expected = "[name2, 22][name3, 31][name1, 43]";
        System.out.println(result);

        assertThat(result.toString(), is(expected));
    }

}