package ru.job4j.bank;

import java.util.*;

/**
 * Created by User2 on 28.04.2018.
 */
public class User {
    private String name, passport;
    private Set<Bank.Account> accounts = new HashSet<>();

    public User(String name, String passport) throws IllegalArgumentException {
        if (name == null || passport == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public String addAccount(Bank.Account account) {
        accounts.add(account);
        return account.getRequisites();
    }

    public Bank.Account getAccountByRequisites(String requisites) throws IllegalArgumentException {
        for (Bank.Account account : accounts) {
            if (account.getRequisites().equals(requisites)) {
                return account;
            }
        }
        throw new IllegalArgumentException();
    }

    public Set<Bank.Account> getAccounts() {
        return accounts;
    }

    public boolean deleteAccount(String requisites) {
        return accounts.remove(getAccountByRequisites(requisites));
    }

    public boolean isMoneyAvailableOnAccount(double amount, String requisites) {
        return getAccountByRequisites(requisites).isMoneyAvailable(amount);
    }

    public double getBalanceOfAccount(String reqs) {
        return getAccountByRequisites(reqs).getBalance();
    }

    public boolean addMoneyToAccount(double amount, String requisites) throws IllegalArgumentException {
        getAccountByRequisites(requisites).addMoney(amount);
        return true;
    }

    public boolean withdrawMoneyFromAccount(double amount, String requisites) {
        getAccountByRequisites(requisites).withdrawMoney(amount);
        return true;
    }

    public void deleteAccounts() {
        accounts.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (!getName().equals(user.getName())) {
            return false;
        }
        return getPassport().equals(user.getPassport());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getPassport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%s : %s]", name, passport);
    }
}
