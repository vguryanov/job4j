package ru.job4j.servlets.servlets;

import ru.job4j.servlets.logic.ValidationService;
import ru.job4j.servlets.persistence.MemoryStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User2 on 14.07.2018.
 */
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(
                "<table>"
        );
        for (MemoryStore.User u : MemoryStore.getInstance().getAll().values()) {
            writer.append("<tr>");
            writer.append(String.format("<td>%s</td>", u));
            writer.append("<td>"
                    + "<form action = '" + req.getContextPath() + "/update' method = 'GET'>"
                    + "<input type = \"submit\" value = 'update'/>"
                    + "<input type=\"hidden\" id=\"hidField1\" name=\"id\" value=\"" + u.getId() + "\">"
                    + "<input type=\"hidden\" id=\"hidField2\" name=\"name\" value=\"" + u.getName() + "\">"
                    + "<input type=\"hidden\" id=\"hidField3\" name=\"login\" value=\"" + u.getLogin() + "\">"
                    + "<input type=\"hidden\" id=\"hidField4\" name=\"email\" value=\"" + u.getEmail() + "\">"
                    + "</form>"
                    + "</td>"
            );
            writer.append("<td>"
                    + "<form action = '" + req.getContextPath() + "/delete' method = 'POST'>"
                    + "<input type = \"submit\" value = 'delete'/>"
                    + "<input type=\"hidden\" id=\"hidField5\" name=\"id\" value=\"" + u.getId() + "\">"
                    + "</form>"
                    + "</td>"
            );
            writer.append("</tr>");
        }
        writer.append(
                "<table>"
        );
        writer.flush();
    }
}
