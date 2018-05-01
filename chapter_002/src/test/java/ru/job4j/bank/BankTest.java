package ru.job4j.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User2 on 29.04.2018.
 */
public class BankTest {
    private Bank testBank = TestUtil.getTestBank();
    private User[] testUsers = TestUtil.getTestUsers();

    @After
    public void after() {
        testBank = TestUtil.refreshBank();
        testUsers = TestUtil.refreshUsers();
    }

    @Test
    public void addUser() throws Exception {
        assertTrue("addUser() test failed", testBank.getAddedUsers().contains(testUsers[0]));
    }

    @Test
    public void addAccount() throws Exception {
        testBank.addAccountToUser(testUsers[0].getPassport());
        assertTrue("addAccount() test failed", testUsers[0].getAccounts().size() == 1);
    }

    @Test
    public void transferMoney() throws Exception {
        String srcReqs = testBank.addAccountToUser(testUsers[0].getPassport());
        String destReqs = testBank.addAccountToUser(testUsers[1].getPassport());
        testUsers[0].addMoneyToAccount(10, srcReqs);

        boolean result = testBank.transferMoney(testUsers[0].getPassport(), srcReqs, testUsers[1].getPassport(), destReqs, 10);
        assertTrue("transferMoney() test failed: unexpected result", result);
        assertTrue("transferMoney() test failed: incorrect accounts values change",
                testUsers[0].getBalanceOfAccount(srcReqs) == 0
                        && testUsers[1].getBalanceOfAccount(destReqs) == 10);

        result = testBank.transferMoney(testUsers[0].getPassport(), srcReqs, testUsers[1].getPassport(), destReqs, 10);
        assertTrue("transferMoney() test failed: transfer was competed with insufficient founds", !result);
    }

    @Test
    public void delete() throws Exception {
        testBank.deleteUser(testUsers[0]);
        assertTrue("user deletion failed", !testBank.getAddedUsers().contains(testUsers[0]));
    }
}