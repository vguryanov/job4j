package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Created by User2 on 20.04.2018.
 */
public class TrackerMenu {
    private final Tracker tracker;
    private final Input input;
    private final BaseAction[] actions = new BaseAction[7];

    public TrackerMenu(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        fillActions();
    }

    private void fillActions() {
        actions[0] = new AddNewItemAction(0, "Adding item to tracker: ");
        actions[1] = new ShowAllItemsAction(1, "Showing all items: ");
        actions[2] = new EditItemAction(2, "Editing item: ");
        actions[3] = new DeleteItemAction(3, "Deleting item: ");
        actions[4] = new FindItemByIdAction(4, "Starting item search by ID: ");
        actions[5] = new FindItemsByNameAction(5, "Starting item search by name: ");
        actions[6] = new ExitAction(6, "Exiting application...");
    }

    public int[] getActionsRange() {
        int[] result = new int[actions.length];
        for (int i = 0; i < actions.length; i++) {
            result[i] = actions[i].getKey();
        }
        return result;
    }

    public void start() {
        BaseAction lastAction = null;
        while (lastAction != actions[6]) {
            this.showMenu();
            int answer = this.input.ask("Выберите пункт меню: ", getActionsRange());

            lastAction = actions[answer];
            System.out.println(lastAction.info());
            lastAction.execute();
        }
    }

    private void showMenu() {
        System.out.println("\nМеню.\n"
                + "0. Add new Item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find item by Id\n"
                + "5. Find items by name\n"
                + "6. Exit Program\n");
    }

    private Item createItem() {
        String name = this.input.ask("Введите ваше имя: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        return item;
    }

    private void addNewItemToTracker() {
        Item item = createItem();
        this.tracker.add(item);
        System.out.println("Item " + item.getId() + " successfully created and added to tracker");
    }

    private class AddNewItemAction extends BaseAction {
        public AddNewItemAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
            addNewItemToTracker();
        }
    }

    private class ShowAllItemsAction extends BaseAction {
        public ShowAllItemsAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
            tracker.showAllItems();
        }
    }

    private class EditItemAction extends BaseAction {
        public EditItemAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
            tracker.showAllItems();
            String idForReplacement = input.ask("Enter ID of task for replace: ");
            tracker.replace(
                    idForReplacement,
                    createItem()
            );
            System.out.println("Item " + idForReplacement + " successfully replaced");
        }
    }

    private class DeleteItemAction extends BaseAction {
        public DeleteItemAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
            tracker.showAllItems();
            tracker.delete(
                    input.ask("Enter ID of task for remove: ")
            );

        }
    }

    private class FindItemByIdAction extends BaseAction {
        public FindItemByIdAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
            System.out.println(
                    tracker.findItemById(
                            input.ask("Enter task ID for search: ")
                    )
            );
        }
    }

    private class FindItemsByNameAction extends BaseAction {
        public FindItemsByNameAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
            Item[] result = tracker.findByName(
                    input.ask("Enter task name for search: ")
            );

            System.out.println(Arrays.toString(result));
        }
    }

    private class ExitAction extends BaseAction {
        public ExitAction(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute() {
        }
    }
}
