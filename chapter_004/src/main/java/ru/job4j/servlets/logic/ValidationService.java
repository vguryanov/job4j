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
    private final LinkedHashMap<Function<String, Boolean>, Function<HttpServletRequest, Boolean>> dispatcher = new LinkedHashMap<>();

    private ValidationService() {
        store = MemoryStore.getInstance();
        this.initDispatcher();
    }

    public static ValidationService getInstance() {
        if (instance == null) {
            instance = new ValidationService();
        }
        return instance;
    }

    private void initDispatcher() {
        dispatcher.put(
                s -> s.equals("add"),
                this::addUser
        );
        dispatcher.put(
                s -> s.equals("update"),
                this::updateUser
        );
        dispatcher.put(
                s -> s.equals("delete"),
                this::deleteUser
        );
    }

    public boolean dispatch(HttpServletRequest req) {
        for (Function<String, Boolean> key : dispatcher.keySet()) {
            if (key.apply(req.getParameter("action"))) {
                return dispatcher.get(key).apply(req);
            }
        }
        return false;
    }

    public boolean deleteUser(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        return store.contains(id) && store.delete(id);
    }

    public boolean updateUser(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        if (!store.contains(id) || !isUserDataValid(name, login, email)) {
            return false;
        }
        return store.update(id, name, login, email);
    }

    public boolean addUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
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
