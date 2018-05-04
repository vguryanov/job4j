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
        CoffeeMachine testCoffeeMachine = new CoffeeMachine(2, 5, 2, 5, 2);
        Integer[] result = testCoffeeMachine.giveChange(12, 1);
        Integer[] expected = new Integer[]{5, 2, 2, 2};
        assertThat(result, is(expected));
    }
}