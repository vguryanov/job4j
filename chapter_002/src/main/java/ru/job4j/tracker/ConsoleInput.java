package ru.job4j.tracker;

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
}
