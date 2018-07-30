package ru.job4j.servlets.logic;

import ru.job4j.servlets.persistence.DBStore;
import ru.job4j.servlets.persistence.Store;
import ru.job4j.servlets.persistence.User;

/**
 * Created by User2 on 12.07.2018.
 */
public class ValidationService {
    private static ValidationService instance;
    private Store<User> store;

    private ValidationService() {
        store = DBStore.getInstance();
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

    public boolean updateUser(int id, String name, String login, String password, String email, String role) {
        if (!store.contains(id)) {
            return false;
        }
        return store.update(id, name, login, password, email, User.Role.valueOf(role));
    }

    public boolean addUser(String name, String login, String password, String email, String role) {
        if (!isUserDataValid(name, login, email)) {
            return false;
        }
        return store.add(name, login, password, email, User.Role.valueOf(role));
    }

    private boolean isUserDataValid(String name, String login, String email) {
        if (name == null && login == null && email == null) {
            return false;
        }
        for (User u : store.getAll().values()) {
            if (u.getName().equals(name) || u.getLogin().equals(login) || u.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
