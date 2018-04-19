package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 19.04.2018.
 */
public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name 1", "test desc", 1));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name 2", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findItemById(item.getId()).getName(), is("test name 2"));
    }

    @Test
    public void whenDeleteItemGetAllReturnsNull() {
        Tracker testTracker = new Tracker();
        Item testItem = testTracker.add(new Item("test name 1", "test desc", 1));
        Input input = new StubInput(new String[]{"3", testItem.getId(), "6"});
        new StartUI(input, testTracker).init();
        assertNull(testTracker.getAll());
    }
}