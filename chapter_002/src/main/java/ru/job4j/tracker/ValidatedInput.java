package ru.job4j.tracker;

/**
 * Created by User2 on 20.04.2018.
 */
public class ValidatedInput implements Input {
    private final Input input;

    public ValidatedInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (IndexOutOfMenuActionsRangeException e) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid key.");
            }
        } while (invalid);
        return value;
    }
}
