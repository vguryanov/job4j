package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by User2 on 16.05.2018.
 */
public class UserTest {
    @Test
    public void firstTest() {
        User user1 = new User("Bob", 2, new GregorianCalendar(2018, 1, 1));
        User user2 = new User("Bob", 2, new GregorianCalendar(2018, 1, 1));

        Map<User, Object> userMap = new HashMap<>();
        userMap.put(user1, null);
        userMap.put(user2, null);

        System.out.println(userMap);
    }
}