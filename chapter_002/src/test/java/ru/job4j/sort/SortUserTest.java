package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 26.04.2018.
 */
public class SortUserTest {
    public List<User> getTestUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("John", 43));
        users.add(new User("Peter", 22));
        users.add(new User("Christopher", 31));
        users.add(new User("Peter", 20));

        return users;
    }

    public String usersToString(Collection<User> users) {
        StringBuilder result = new StringBuilder();
        for (User user : users) {
            result.append(user);
        }
        return result.toString();
    }

    @Test
    public void sort() throws Exception {
        Set<User> resultUserSet = new SortUser().sort(getTestUsers());
        String result = usersToString(resultUserSet);
        String expected = "[Peter, 20][Peter, 22][Christopher, 31][John, 43]";
        assertThat(result, is(expected));
    }

    @Test
    public void sortNameLength() {
        List<User> users = new SortUser().sortNameLength(getTestUsers());
        String result = usersToString(users);
        String expected = "[John, 43][Peter, 22][Peter, 20][Christopher, 31]";
        assertThat(result, is(expected));
    }

    @Test
    public void sortByAllFields() {
        List<User> users = new SortUser().sortByAllFields(getTestUsers());
        String result = usersToString(users);
        String expected = "[Christopher, 31][John, 43][Peter, 20][Peter, 22]";
        assertThat(result, is(expected));
    }
}