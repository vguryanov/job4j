package ru.job4j;

import java.util.*;

/**
 * Created by User2 on 03.05.2018.
 */
public class CoffeeMachine {
    ArrayList<Integer> cash = new ArrayList<>();

    public CoffeeMachine(int... cash) {
        for (int i : cash) {
            this.cash.add(i);
        }
        Collections.sort(this.cash, Collections.reverseOrder());
    }

    public Integer[] giveChange(int moneyTaken, int price) throws IllegalArgumentException {
        if (moneyTaken < price) {
            throw new IllegalArgumentException();
        }

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> usedCash = new ArrayList<>();
        if (moneyTaken == price) {
            result.add(0);
        } else {
            int changeAmount = moneyTaken - price;
            for (int shiftIndex = 0; changeAmount != 0 && shiftIndex < cash.size(); shiftIndex++) {
                for (int i = shiftIndex; changeAmount != 0 && i < cash.size(); i++) {
                    int value = cash.get(i);
                    if (value <= changeAmount) {
                        changeAmount -= value;
                        result.add(value);
                        usedCash.add(value);
                    }
                }
                if (changeAmount == 0) {
                    removeCash(usedCash);
                } else {
                    changeAmount = moneyTaken - price;
                    result.clear();
                    usedCash.clear();
                }
            }
        }
        return result.toArray(new Integer[result.size()]);
    }

    public void removeCash(ArrayList<Integer> cash) {
        for (int i : cash) {
            this.cash.remove(this.cash.indexOf(i));
        }
    }
}
