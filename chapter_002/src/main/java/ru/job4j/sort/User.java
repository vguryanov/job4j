package ru.job4j.sort;

/**
 * Created by User2 on 26.04.2018.
 */
public class User implements Comparable<User> {
    private String name;
    private Integer age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.getAge());
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", name, age);
    }
}
