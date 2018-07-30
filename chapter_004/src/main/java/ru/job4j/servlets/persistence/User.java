package ru.job4j.servlets.persistence;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by User2 on 18.07.2018.
 */
public class User {
    private static int idCounter;

    private int id;
    private String name, login, password, email;
    private Timestamp creationDate;
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public User(String name, String login, String password, String email, Role role) {
        this.id = ++idCounter;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.creationDate = new Timestamp(new Date().getTime());
    }

    public User(int id, String name, String login, String password, String email, Timestamp creationDate, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format("%d : %s : %s : %s : %s : %s", id, role, name, login, email, creationDate);
    }
}
