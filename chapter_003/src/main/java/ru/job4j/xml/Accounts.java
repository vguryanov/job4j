package ru.job4j.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by User2 on 20.06.2018.
 */

@XmlRootElement
public class Accounts {
    private List<Account> accounts;

    public Accounts() {
    }

    Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @XmlElement(name = "account")
    public List<Account> getAccounts() {
        return accounts;
    }
}
