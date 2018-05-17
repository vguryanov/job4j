package ru.job4j.bank;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User2 on 28.04.2018.
 */
public class Bank {
    private static String bankInfo;
    private Set<User> addedUsers = new HashSet<>();

    public Set<User> getAddedUsers() {
        return Collections.unmodifiableSet(addedUsers);
    }

    public boolean addUser(User user) {
        return addedUsers.add(user);
    }

    public String addAccountToUser(String passport) throws IllegalArgumentException {
        if (passport == null) {
            throw new IllegalArgumentException();
        }
        return getUserByPassport(passport).addAccount(new Bank.Account());
    }

    public User getUserByPassport(String passport) throws IllegalArgumentException {
        for (User user : addedUsers) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean transferMoney(String srcPassport, String srcRequisites, String destPassport, String dstRequisites, double amount) {
        User source = getUserByPassport(srcPassport);
        User dest = getUserByPassport(destPassport);
        if (!source.isMoneyAvailableOnAccount(amount, srcRequisites)) {
            return false;
        }

        source.withdrawMoneyFromAccount(amount, srcRequisites);
        dest.addMoneyToAccount(amount, dstRequisites);
        return true;
    }

    public void deleteUser(User user) {
        user.deleteAccounts();
        addedUsers.remove(user);
    }

    public static class Account {
        private double value;
        private String requisites;
        private static int counter = 0;

        private Account() throws IllegalArgumentException {
            counter++;
            value = 0;
            requisites = bankInfo + " " + counter;
        }

        public double getValue() {
            return value;
        }

        public String getRequisites() {
            return requisites;
        }

        public double getBalance() {
            return value;
        }

        public boolean isMoneyAvailable(double amount) {
            return this.value >= amount;
        }

        public void addMoney(double amount) {
            value += amount;
        }

        public void withdrawMoney(double amount) {
            value -= amount;
        }
    }
}
