package ru.job4j.tracker;

/**
 * Created by User2 on 20.04.2018.
 */
public class ValidatedInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        int result = -1;
        boolean isValidationSuccess = false;
        while (!isValidationSuccess) {
            try {
                result = super.ask(question, range);
                isValidationSuccess = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input key format, please try again");
            } catch (IndexOutOfMenuActionsRangeException e) {
                System.out.println("Wrong input key, please try again");
            }
        }
        return result;
    }
}
