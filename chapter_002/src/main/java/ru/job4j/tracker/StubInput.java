package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Created by User2 on 19.04.2018.
 */
public class StubInput implements Input {
    /**
     * Это поле содержит последовательность ответов пользователя.
     * Например. Если пользователь
     * хочет выбрать добавление новой заявки ему нужно ввести:
     * 0 - выбор пункта меня "добавить новую заявку".
     * name - имя заявки
     * desc - описание заявки
     * y - выйти из трекера.
     */
    private final ArrayList<String> value;

    /**
     * Поле считает количество вызовом метода ask.
     * При каждом вызове надо передвинуть указатель на новое число.
     */
    private int position;

    public StubInput(final ArrayList<String> value) {
        this.value = value;
    }

    /**
     * Давайте рассмотрим, как работает этот метод.
     * у нас есть объект в котором содержатся заранее продуманные ответы.
     * При последовательном вызове метода ask нам надо возвращать соответствующие данные.
     * Как если бы мы симулировали поведение пользователя.
     * Для этого при каждом вызове метода ask мы увеличиваем счетчик и
     * при следующем вызове он вернет нам новое значение.
     */
    @Override
    public String ask(String question) {
        return this.value.get(this.position++);
    }

    @Override
    public int ask(String question, ArrayList<Integer> range) {
        int inputKey = Integer.parseInt(ask(question));
        int result = -1;

        for (int i : range) {
            if (i == inputKey) {
                result = i;
            }
        }

        if (result == -1) {
            throw new IndexOutOfMenuActionsRangeException("Key is out of menu actions range");
        }
        return result;
    }
}
