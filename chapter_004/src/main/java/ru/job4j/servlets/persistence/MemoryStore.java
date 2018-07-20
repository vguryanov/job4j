package ru.job4j.servlets.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by User2 on 12.07.2018.
 */
public class MemoryStore implements Store<User> {
    private volatile static MemoryStore instance;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static Map<Integer, User> users = new HashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        synchronized (lock.writeLock()) {
            if (instance == null) {
                instance = new MemoryStore();
            }
            return instance;
        }
    }

    @Override
    public boolean add(String name, String login, String email) {
        synchronized (lock.writeLock()) {
            User u = new User(name, login, email);
            users.put(u.getId(), u);
            return true;
        }
    }

    @Override
    public boolean update(int id, String name, String login, String email) {
        synchronized (lock.writeLock()) {
            User u = users.get(id);
            if (name != null) {
                u.setName(name);
            }
            if (login != null) {
                u.setLogin(login);
            }
            if (email != null) {
                u.setEmail(email);
            }
            return true;
        }
    }

    @Override
    public boolean delete(int id) {
        synchronized (lock.writeLock()) {
            users.remove(id);
            return true;
        }
    }

    @Override
    public Map<Integer, User> getAll() {
        synchronized (lock.readLock()) {
            return Collections.unmodifiableMap(users);
        }
    }

    @Override
    public User findById(int id) {
        synchronized (lock.readLock()) {
            return users.get(id);
        }
    }

    @Override
    public boolean contains(int id) {
        synchronized (lock.readLock()) {
            return users.containsKey(id);
        }
    }

}
