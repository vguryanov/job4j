package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by User2 on 18.04.2018.
 */
public class ConsoleInput implements Input {
    @Override
    public String ask(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, ArrayList<Integer> range) throws NumberFormatException, IndexOutOfMenuActionsRangeException {
        int inputKey = Integer.parseInt(ask(question));
        int result = -1;

        for (int i : range) {
            if (i == inputKey) {
                result = i;
            }
        }

        if (result == -1) {
            throw new IndexOutOfMenuActionsRangeException("Key is out of menu actions range");
        }
        return result;
    }
}
