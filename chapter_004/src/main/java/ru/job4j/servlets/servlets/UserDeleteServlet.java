package ru.job4j.servlets.servlets;

import ru.job4j.servlets.logic.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User2 on 14.07.2018.
 */
public class UserDeleteServlet extends HttpServlet {
    private ValidationService vs = ValidationService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(vs.deleteUser(id)));
        writer.flush();
    }
}
