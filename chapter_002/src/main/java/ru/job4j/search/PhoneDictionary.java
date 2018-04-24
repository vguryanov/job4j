package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User2 on 24.04.2018.
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();

        for (Person person : persons) {
            if (person.toString().contains(key)) {
                result.add(person);
            }
        }

        return result;
    }
}
