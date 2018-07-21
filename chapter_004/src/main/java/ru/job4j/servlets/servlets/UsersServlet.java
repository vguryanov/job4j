package ru.job4j.servlets.servlets;

import ru.job4j.servlets.persistence.DBStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User2 on 14.07.2018.
 */
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", DBStore.getInstance().getAll().values());
        req.getRequestDispatcher("\\WEB-INF\\views\\users.jsp").forward(req, resp);
    }
}
