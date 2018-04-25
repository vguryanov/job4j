package ru.job4j.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User2 on 25.04.2018.
 */
public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();

        for (User user : list) {
            result.put(user.getId(), user);
        }

        return result;
    }
}
