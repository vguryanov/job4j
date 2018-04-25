package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 25.04.2018.
 */
public class UserConvertTest {
    @Test
    public void process() throws Exception {
        User user1 = new User("name1", "city1");
        User user2 = new User("name2", "city2");

        List<User> testList = new ArrayList<>();
        testList.add(user1);
        testList.add(user2);

        HashMap<Integer, User> result = new UserConvert().process(testList);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(user1.getId(), user1);
        expected.put(user2.getId(), user2);

        assertThat(result, is(expected));
    }

}