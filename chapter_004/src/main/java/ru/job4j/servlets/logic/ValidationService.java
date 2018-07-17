package ru.job4j.servlets.logic;

import ru.job4j.servlets.persistence.MemoryStore;
import ru.job4j.servlets.persistence.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * Created by User2 on 12.07.2018.
 */
public class ValidationService {
    private static ValidationService instance;
    private Store<MemoryStore.User> store;

    private ValidationService() {
        store = MemoryStore.getInstance();
    }

    public static ValidationService getInstance() {
        if (instance == null) {
            instance = new ValidationService();
        }
        return instance;
    }

    public boolean deleteUser(int id) {
        return store.contains(id) && store.delete(id);
    }

    public boolean updateUser(int id, String name, String login, String email) {
        if (!store.contains(id) || !isUserDataValid(name, login, email)) {
            return false;
        }
        return store.update(id, name, login, email);
    }

    public boolean addUser(String name, String login, String email) {
        if (!isUserDataValid(name, login, email)) {
            return false;
        }
        return store.add(name, login, email);
    }

    private boolean isUserDataValid(String name, String login, String email) {
        if (name == null && login == null && email == null) {
            return false;
        }
        for (MemoryStore.User u : store.getAll().values()) {
            if (u.getName().equals(name) || u.getLogin().equals(login) || u.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
