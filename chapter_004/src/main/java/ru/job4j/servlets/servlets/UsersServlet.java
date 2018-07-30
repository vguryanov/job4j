package ru.job4j.servlets.servlets;

import ru.job4j.servlets.persistence.DBStore;
import ru.job4j.servlets.persistence.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User2 on 14.07.2018.
 */
public class UsersServlet extends HttpServlet {
    private DBStore dbStore = DBStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", dbStore.getAll().values());
        req.setAttribute("role", dbStore.getRoleForLogin((String) req.getSession().getAttribute("login")).toString());
        req.getRequestDispatcher("\\WEB-INF\\views\\users.jsp").forward(req, resp);
    }
}
