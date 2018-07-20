package ru.job4j.servlets.persistence;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by User2 on 18.07.2018.
 */
public class User {
    private static int idCounter;

    private int id;
    private String name, login, email;
    private Timestamp creationDate;

    User(String name, String login, String email) {
        this.id = ++idCounter;
        this.name = name;
        this.login = login;
        this.email = email;
        this.creationDate = new Timestamp(new Date().getTime());
    }

    public User(int id, String name, String login, String email, Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format("%d : %s : %s : %s : %s", id, name, login, email, creationDate);
    }
}
