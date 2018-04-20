package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Created by User2 on 18.04.2018.
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Выберите пункт меню: ");
            switch (answer) {
                case ADD:
                    this.addItemToTracker();
                    break;

                case SHOW:
                    this.tracker.showAllItems();
                    break;

                case EDIT:
                    this.tracker.showAllItems();
                    this.tracker.replace(
                            this.input.ask("Enter ID of task for replace: "),
                            this.createItem()
                    );
                    break;

                case DELETE:
                    this.tracker.showAllItems();
                    this.tracker.delete(
                            this.input.ask("Enter ID of task for remove: "));
                    break;

                case FINDBYID:
                    System.out.println(
                            this.tracker.findItemById(
                                    this.input.ask("Enter task ID for search: ")
                            )
                    );
                    break;

                case FINDBYNAME:
                    Item[] result = this.tracker.findByName(
                            this.input.ask("Enter task name for search: ")
                    );

                    System.out.println(Arrays.toString(result));
                    break;

                case EXIT:
                    exit = true;
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private Item createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        System.out.println("Item created");
        return item;
    }

    private void addItemToTracker() {
        Item item = createItem();
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
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
        // добавить остальные пункты меню.
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}