package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 19.04.2018.
 */
public class StartUITest {
//    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//    private final PrintStream stdout = System.out;
//
//    public void redirectOutput() {
//        System.setOut(new PrintStream(this.out));
//    }
//
//    public void setDefaultOutput() {
//        System.setOut(this.stdout);
//    }
//
//    public StubInput getTestStubInput(String... inputLines) {
//        ArrayList<String> inputList = new ArrayList<>();
//        for (String s : inputLines) {
//            inputList.add(s);
//        }
//        return new StubInput(inputList);
//    }
//
//    @Test
//    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
//        Tracker tracker = new Tracker();     // создаём Tracker
//        Input input = new ValidatedInput(
//                getTestStubInput(
//                        new String[]{"0", "test name", "desc", "6"}
//                )
//        );   //создаём StubInput с последовательностью действий
//        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
//        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
//    }
//
//    @Test
//    public void whenUpdateThenTrackerHasUpdatedValue() {
//        // создаём Tracker
//        Tracker tracker = new Tracker();
//        //Напрямую добавляем заявку
//        Item item = tracker.add(new Item("test name 1", "test desc", 1));
//        //создаём StubInput с последовательностью действий
//        Input input = new ValidatedInput(
//                getTestStubInput(
//                        new String[]{"2", item.getId(), "test name 2", "desc", "6"}
//                )
//        );
//        // создаём StartUI и вызываем метод init()
//        new StartUI(input, tracker).init();
//        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
//        assertThat(tracker.findItemById(item.getId()).getName(), is("test name 2"));
//    }
//
//    @Test
//    public void whenDeleteItemGetAllReturnsNull() {
//        Tracker testTracker = new Tracker();
//        Item testItem = testTracker.add(new Item("test name 1", "test desc", 1));
//        Input input = new ValidatedInput(
//                getTestStubInput(
//                        new String[]{"3", testItem.getId(), "6"}
//                )
//        );
//        new StartUI(input, testTracker).init();
//        assertNull(testTracker.getAll());
//    }
//
//    @Test
//    public void showAllItemsTest() {
//        redirectOutput();
//
//        Tracker testTracker = new Tracker();
//        Item testItem = testTracker.add(new Item("test name 1", "test desc", 1));
//        Input input = new ValidatedInput(
//                getTestStubInput(
//                        new String[]{"1", "6"}
//                )
//        );
//        new StartUI(input, testTracker).init();
//        assertTrue("showAllItemsTest() failed", this.out.toString().contains("name: test name 1, desc: test desc"));
//
//        setDefaultOutput();
//    }
//
//    @Test
//    public void findItemByIdTest() {
//        redirectOutput();
//
//        Tracker testTracker = new Tracker();
//        Item testItem = testTracker.add(new Item("test name 1", "test desc", 1));
//        Input input = new ValidatedInput(
//                getTestStubInput(
//                        new String[]{"4", testItem.getId(), "6"}
//                )
//        );
//        new StartUI(input, testTracker).init();
//        assertTrue("findItemByIdTest() failed", this.out.toString().contains("name: test name 1, desc: test desc"));
//
//        setDefaultOutput();
//    }
//
//    @Test
//    public void findItemByNameTest() {
//        redirectOutput();
//
//        Tracker testTracker = new Tracker();
//        Item testItem = testTracker.add(new Item("test name 1", "test desc", 1));
//        Input input = new ValidatedInput(
//                getTestStubInput(
//                        new String[]{"5", testItem.getName(), "6"}
//                )
//        );
//        new StartUI(input, testTracker).init();
//        assertTrue("findItemByNameTest() failed", this.out.toString().contains("name: test name 1, desc: test desc"));
//
//        setDefaultOutput();
//    }
//
//    @Test
//    public void validationTest() {
//        redirectOutput();
//
//        ValidatedInput input = new ValidatedInput(
//                getTestStubInput(new String[]{"invalid", "0"})
//        );
//
//        ArrayList<Integer> range = new ArrayList<>();
//        range.add(0);
//
//        input.ask("Введите пункт меню: ", range);
//        assertThat(
//                this.out.toString(),
//                is(
//                        String.format("Please enter valid key." + System.lineSeparator())
//                )
//        );
//
//        setDefaultOutput();
//    }
}