package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by User2 on 16.04.2018.
 */
public class TrackerTest {
    private static Item[] getTestItems(int beginIndex, int endIndex) {
        Item[] items = new Item[4];
        items[0] = new Item("item 1", "", 1);
        items[1] = new Item("item 2", "", 2);
        items[2] = new Item("item 2", "", 3);
        items[3] = new Item("item 4", "", 4);

        Item[] result = new Item[endIndex - beginIndex + 1];

        for (int i = beginIndex - 1, j = 0; i < endIndex; i++, j++) {
            result[j] = items[i];
        }

        return result;
    }

    private static Tracker getTestTracker(int beginIndex, int endIndex) {
        Tracker testTracker = new Tracker();
        for (Item item : getTestItems(beginIndex, endIndex)) {
            testTracker.add(item);
        }
        return testTracker;
    }

    @Test
    public void getAll() {
        Tracker testTracker = getTestTracker(1, 4);
        Item[] expected = getTestItems(1, 4);
        Item[] result = testTracker.getAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findItemById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void findItemById() throws Exception {
        Tracker testTracker = getTestTracker(1, 4);
        String testID = testTracker.getAll()[0].getId();

        Item expected = testTracker.getAll()[0];
        Item result = testTracker.findItemById(testID);

        assertThat(result, is(expected));
    }

    @Test
    public void findByName() throws Exception {
        Tracker testTracker = getTestTracker(1, 4);

        String testName = getTestItems(1, 2)[1].getName();
        Item[] result = testTracker.findByName(testName);
        Item[] expected = getTestItems(2, 3);

        assertThat(result, is(expected));
    }

    @Test
    public void delete() throws Exception {
        Tracker testTracker = getTestTracker(1, 4);
        String testID = testTracker.getAll()[0].getId();
        testTracker.delete(testID);

        Item[] result = testTracker.getAll();
        Item[] expected = getTestItems(2, 4);

        assertThat(result, is(expected));
    }
}