package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Created by User2 on 20.04.2018.
 */
public class TrackerMenu {
    private final Tracker tracker;
    private final Input input;
    private final MenuAction[] actions = new MenuAction[7];

    public TrackerMenu(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        fillActions();
    }

    private void fillActions() {
        actions[0] = new AddNewItemAction();
        actions[1] = new ShowAllItemsAction();
        actions[2] = new EditItemAction();
        actions[3] = new DeleteItemAction();
        actions[4] = new FindItemByIdAction();
        actions[5] = new FindItemsByNameAction();
        actions[6] = new ExitAction();
    }

    public void start() {
        MenuAction lastAction = null;
        while (lastAction != actions[6]) {
            this.showMenu();
            String answer = this.input.ask("Выберите пункт меню: ");

            try {
                lastAction = actions[Integer.parseInt(answer)];
                System.out.println(lastAction.info());
                lastAction.execute();
            } catch (NumberFormatException e) {
                System.out.println("Wrong key");
            }
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
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        return item;
    }

    private void addNewItemToTracker() {
        Item item = createItem();
        this.tracker.add(item);
        System.out.println("Item " + item.getId() + " successfully created and added to tracker");
    }

    private class AddNewItemAction implements MenuAction {
        @Override
        public void execute() {
            addNewItemToTracker();
        }

        @Override
        public String info() {
            return "Adding item to tracker: ";
        }
    }

    private class ShowAllItemsAction implements MenuAction {
        @Override
        public void execute() {
            tracker.showAllItems();
        }

        @Override
        public String info() {
            return "Showing all items: ";
        }
    }

    private class EditItemAction implements MenuAction {
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

        @Override
        public String info() {
            return "Editing item: ";
        }
    }

    private class DeleteItemAction implements MenuAction {
        @Override
        public void execute() {
            tracker.showAllItems();
            tracker.delete(
                    input.ask("Enter ID of task for remove: ")
            );

        }

        @Override
        public String info() {
            return "Deleting item: ";
        }
    }

    private class FindItemByIdAction implements MenuAction {
        @Override
        public void execute() {
            System.out.println(
                    tracker.findItemById(
                            input.ask("Enter task ID for search: ")
                    )
            );
        }

        @Override
        public String info() {
            return "Starting item search by ID: ";
        }
    }

    private class FindItemsByNameAction implements MenuAction {
        @Override
        public void execute() {
            Item[] result = tracker.findByName(
                    input.ask("Enter task name for search: ")
            );

            System.out.println(Arrays.toString(result));
        }

        @Override
        public String info() {
            return "Starting item search by name: ";
        }
    }

    private class ExitAction implements MenuAction {
        @Override
        public void execute() {
        }

        @Override
        public String info() {
            return "Exiting application...";
        }
    }
}
