package ru.job4j.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by User2 on 19.06.2018.
 */

public class Account {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "intValue")
    private int intValue;

    Account(String name, String password, int intValue) {
        this.name = name;
        this.password = password;
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        return name + ":" + password + ":" + intValue;
    }
}
