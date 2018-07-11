package ru.job4j.servlets;

import java.util.*;

/**
 * Created by User2 on 08.07.2018.
 */
public class User {
    private static int idCounter;
    private static Map<Integer, User> users = new HashMap<>();

    private int id;
    private String name, login, email;
    private Date creationDate;

    public User(String name, String login, String email) {
        this.id = ++idCounter;
        this.name = name;
        this.login = login;
        this.email = email;
        this.creationDate = new Date();
        users.put(id, this);
    }

    public static Map<Integer, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    public static void removeUser(int id) {
        users.remove(id);
    }

    public static void removeUser(String id) {
        removeUser(Integer.parseInt(id));
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
