package ru.job4j.servlets.persistence;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by User2 on 12.07.2018.
 */
public class MemoryStore implements Store<MemoryStore.User> {
    private volatile static MemoryStore instance;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static Map<Integer, User> users = new HashMap<>();

    private MemoryStore() {
        add("testname", "testlog", "testmail");
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

    public static class User {
        private static int idCounter;

        private int id;
        private String name, login, email;
        private Date creationDate;

        private User(String name, String login, String email) {
            this.id = ++idCounter;
            this.name = name;
            this.login = login;
            this.email = email;
            this.creationDate = new Date();
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        @Override
        public String toString() {
            return String.format("%d : %s : %s : %s : %s", id, name, login, email, creationDate);
        }
    }
}
