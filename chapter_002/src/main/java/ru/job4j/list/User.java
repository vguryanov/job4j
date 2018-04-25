package ru.job4j.list;

/**
 * Created by User2 on 25.04.2018.
 */
public class User {
    private static int idCounter = 0;

    private int id;
    private String name, city;

    public User(String name, String city) {
        this.id = ++idCounter;
        this.name = name;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            throw new IllegalArgumentException();
        }

        User object = (User) obj;

        return this.id == object.id && this.name.equals(object.getName()) && this.city.equals(object.getCity());
    }
}
