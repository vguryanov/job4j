package ru.job4j.bank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 28.04.2018.
 */
public class UserTest {
    @Before
    public void fillBank() {
        BankTest.fillWithTestUsers();
    }

    @After
    public void deleteAllUsers() {
        for (User user : BankTest.TEST_USERS) {
            Bank.delete(user);
        }
    }

    @Test
    public void getAccounts() throws Exception {
        User testUser = BankTest.TEST_USERS[0];
        String reqs1 = Bank.addAccountToUser(testUser.getPassport());
        String reqs2 = Bank.addAccountToUser(testUser.getPassport());
        Set<Bank.Account> result = testUser.getAccounts();
        Set<Bank.Account> expected = new HashSet<>();
        expected.add(testUser.getAccountByRequisites(reqs1));
        expected.add(testUser.getAccountByRequisites(reqs2));
        assertThat(result, is(expected));
    }

    @Test
    public void deleteAccount() throws Exception {
        User testUser = BankTest.TEST_USERS[0];
        String reqs1 = Bank.addAccountToUser(testUser.getPassport());
        testUser.deleteAccount(reqs1);
        int result = testUser.getAccounts().size();
        int expected = 0;
        assertThat(result, is(expected));
    }
}