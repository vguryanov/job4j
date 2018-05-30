package ru.job4j.userstorage;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by User2 on 27.05.2018.
 */

@ThreadSafe
public class UserStorage {
    private Map<Integer, User> users = new ConcurrentHashMap<>();

    boolean add(User user) {
        return users.put(user.getId(), user) != null;
    }

    boolean delete(User user) {
        return users.remove(user) != null;
    }

    synchronized void transfer(int fromId, int toId, int amount) {
        users.get(fromId).addAmount(-amount);
        users.get(toId).addAmount(amount);
    }
}
