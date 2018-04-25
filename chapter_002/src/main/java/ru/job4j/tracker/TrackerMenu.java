package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User2 on 20.04.2018.
 */
public class TrackerMenu {
    private final Tracker tracker;
    private final Input input;
    private final ArrayList<BaseAction> actions = new ArrayList<>();

    public TrackerMenu(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        fillActions();
    }

    private void fillActions() {
        actions.add(new AddNewItemAction(0, "Adding item to tracker: "));
        actions.add(new ShowAllItemsAction(1, "Showing all items: "));
        actions.add(new EditItemAction(2, "Editing item: "));
        actions.add(new DeleteItemAction(3, "Deleting item: "));
        actions.add(new FindItemByIdAction(4, "Starting item search by ID: "));
        actions.add(new FindItemsByNameAction(5, "Starting item search by name: "));
        actions.add(new ExitAction(6, "Exiting application..."));
    }

    public ArrayList<Integer> getActionsRange() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            result.add(actions.get(i).getKey());
        }
        return result;
    }

    public void start() {
        BaseAction lastAction = null;
        while (lastAction != actions.get(6)) {
            this.showMenu();
            int answer = this.input.ask("Выберите пункт меню: ", getActionsRange());

            lastAction = actions.get(answer);
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
            ArrayList<Item> result = tracker.findByName(
                    input.ask("Enter task name for search: ")
            );

            for (Item item : result) {
                System.out.println(item);
            }
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
