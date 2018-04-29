package ru.job4j.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 29.04.2018.
 */
public class BankTest {
    public static final User[] TEST_USERS = new User[4];

    static {
        for (int i = 0; i < TEST_USERS.length; i++) {
            TEST_USERS[i] = new User("test name " + i, "test passport " + i);
        }
    }

    public static void fillWithTestUsers() {
        for (User user : TEST_USERS) {
            Bank.addUser(user);
        }
    }

    @Before
    public void prepareBank() {
        fillWithTestUsers();
    }

    @After
    public void deleteAllUsers() {
        for (User user : TEST_USERS) {
            Bank.delete(user);
        }
    }

    @Test
    public void addUser() throws Exception {
        assertTrue("addUser() test failed", Bank.getAddedUsers().contains(TEST_USERS[0]));
    }

    @Test
    public void addAccount() throws Exception {
        Bank.addAccountToUser(TEST_USERS[0].getPassport());
        assertTrue("addAccount() test failed", TEST_USERS[0].getAccounts().size() == 1);
    }

    @Test
    public void transferMoney() throws Exception {
        String srcReqs = Bank.addAccountToUser(TEST_USERS[0].getPassport());
        String destReqs = Bank.addAccountToUser(TEST_USERS[1].getPassport());
        TEST_USERS[0].addMoneyToAccount(10, srcReqs);

        boolean result = Bank.transferMoney(TEST_USERS[0].getPassport(), srcReqs, TEST_USERS[1].getPassport(), destReqs, 10);
        assertTrue("transferMoney() test failed: unexpected result", result);
        assertTrue("transferMoney() test failed: incorrect accounts values change",
                TEST_USERS[0].getBalanceOfAccount(srcReqs) == 0
                        && TEST_USERS[1].getBalanceOfAccount(destReqs) == 10);

        result = Bank.transferMoney(TEST_USERS[0].getPassport(), srcReqs, TEST_USERS[1].getPassport(), destReqs, 10);
        assertTrue("transferMoney() test failed: transfer was competed with insufficient founds", !result);
    }

    @Test
    public void delete() throws Exception {
        Bank.delete(TEST_USERS[0]);
        assertTrue("user deletion failed", !Bank.getAddedUsers().contains(TEST_USERS[0]));
    }

}