package ru.job4j.bank;

/**
 * Created by User2 on 01.05.2018.
 */
public class TestUtil {
    private static Bank testBank = new Bank();
    private static final User[] TEST_USERS = new User[4];

    static {
        createTestUsers();
        fillBankWithTestUsers();
    }

    public static void createTestUsers() {
        for (int i = 0; i < TEST_USERS.length; i++) {
            TEST_USERS[i] = new User("test name " + i, "test passport " + i);
        }
    }

    public static User[] getTestUsers() {
        return TEST_USERS;
    }

    public static void fillBankWithTestUsers() {
        for (User user : TEST_USERS) {
            testBank.addUser(user);
        }
    }

    public static Bank getTestBank() {
        return testBank;
    }

    public static Bank refreshBank() {
        testBank = new Bank();
        return testBank;
    }

    public static User[] refreshUsers() {
        createTestUsers();
        fillBankWithTestUsers();
        return TEST_USERS;
    }
}
