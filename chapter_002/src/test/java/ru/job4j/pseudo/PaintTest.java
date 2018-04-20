package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {
    public void drawToByteStream(Shape shape, String expected) {
        // получаем ссылку на стандартный вывод в консоль.
        PrintStream stdout = System.out;
        // Создаем буфур для хранения вывода.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Заменяем стандартный вывод на вывод в пямять для тестирования.
        System.setOut(new PrintStream(out));
        // выполняем действия пишушиее в консоль.
        new Paint().draw(shape);
        // проверяем результат вычисления
        assertThat(new String(out.toByteArray()), is(expected));
        // возвращаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }

    @Test
    public void whenDrawSquare() {
        drawToByteStream(new Square(),
                new StringBuilder()
                        .append("+++++")
                        .append("+++++")
                        .append("+++++")
                        .append(System.lineSeparator())
                        .toString()

        );
    }

    @Test
    public void whenDrawTriangle() {
        drawToByteStream(new Triangle(),
                new StringBuilder()
                        .append("  +  ")
                        .append(" +++ ")
                        .append("+++++")
                        .append(System.lineSeparator())
                        .toString()
        );
    }
}