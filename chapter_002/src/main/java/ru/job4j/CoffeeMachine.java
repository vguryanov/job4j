package ru.job4j;

import java.util.*;

/**
 * Created by User2 on 03.05.2018.
 */
public class CoffeeMachine {
    private int[] cashValues = new int[]{10, 5, 2, 1};

    public Integer[] giveChange(int moneyTaken, int price) throws IllegalArgumentException {
        if (moneyTaken < price) {
            throw new IllegalArgumentException();
        }

        ArrayList<Integer> result = new ArrayList<>();
        if (moneyTaken == price) {
            result.add(0);
        } else {
            int changeAmount = moneyTaken - price;
            int cashValuesIndex = 0;
            while (changeAmount > 0) {
                while (changeAmount >= cashValues[cashValuesIndex]) {
                    result.add(cashValues[cashValuesIndex]);
                    changeAmount -= cashValues[cashValuesIndex];
                }
                cashValuesIndex++;
            }
        }
        return result.toArray(new Integer[result.size()]);
    }
}
