package ru.job4j.tracker;

/**
 * Created by User2 on 16.04.2018.
 */
public class Item {
    private String id, name, description, comments;
    private long created;

    public Item(String name, String description, long created) {
        if (name != null && description != null && created != 0) {
            this.name = name;
            this.description = description;
            this.created = created;
        } else {
            throw new NullPointerException();
        }
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

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
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
