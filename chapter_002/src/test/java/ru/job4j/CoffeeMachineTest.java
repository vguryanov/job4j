package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 04.05.2018.
 */
public class CoffeeMachineTest {
    @Test
    public void giveChange() throws Exception {
        CoffeeMachine testCoffeeMachine = new CoffeeMachine();
        Integer[] result = testCoffeeMachine.giveChange(73, 35);
        Integer[] expected = new Integer[]{10, 10, 10, 5, 2, 1};
        assertThat(result, is(expected));
    }
}