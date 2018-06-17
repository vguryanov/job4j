package ru.job4j.tracker;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

/**
 * Created by User2 on 16.04.2018.
 */
public class Item {
    private String id, name, description, comments;
    private Timestamp created;

    public Item(String name, String description) {
        if (name != null && description != null) {
            this.name = name;
            this.description = description;
            this.comments = "";
        } else {
            throw new NullPointerException();
        }
    }

    public Item(String id, String name, String description, String comments, Timestamp created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.comments = comments;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", id, name, description, comments, created);
    }

    @Override
    public boolean equals(Object obj) {
        Item object = (Item) obj;
        return name.equals(object.getName()) && description.equals(object.getDescription()) && created == object.getCreated();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
