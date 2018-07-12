package ru.job4j.servlets.logic;

import ru.job4j.servlets.persistence.MemoryStore;
import ru.job4j.servlets.persistence.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * Created by User2 on 12.07.2018.
 */
public class ValidateService {
    private static ValidateService instance;
    private Store<MemoryStore.User> store;
    private final LinkedHashMap<Function<String, Boolean>, Function<HttpServletRequest, Boolean>> dispatcher = new LinkedHashMap<>();

    private ValidateService() {
        store = MemoryStore.getInstance();
        this.initDispatcher();
    }

    public static ValidateService getInstance() {
        if (instance == null) {
            instance = new ValidateService();
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

    private boolean deleteUser(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        if (!store.contains(id)) {
            return false;
        }
        return store.delete(id);
    }

    private boolean updateUser(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        if (!store.contains(id)) {
            return false;
        }
        return store.update(id, name);
    }

    private boolean addUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        boolean isDataValid = true;
        for (MemoryStore.User u : store.getAll().values()) {
            if (u.getLogin().equals(login) || u.getEmail().equals(email)) {
                isDataValid = false;
                break;
            }
        }
        if (!isDataValid) {
            return false;
        }
        return store.add(name, login, email);
    }
}
