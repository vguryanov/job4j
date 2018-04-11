package ru.job4j.condition;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DummyBot {
    /**
     * Отвечает на вопросы.
     *
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";

        if (question.equals("Привет, Бот.")) {
            // заменить ... на правильный ответ rsl = "ответ по заданию".
            rsl = "Привет, умник.";
        } else if (question.equals("Пока.")) { // заменить ... на проверку, что этот вопрос известен боту и он знает как на него ответить.
            // заменить ... на правильный ответ rsl = "ответ по заданию".
            rsl = "До скорой встречи.";
        }

        return rsl;
    }
}

