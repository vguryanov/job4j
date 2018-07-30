package ru.job4j.servlets.servlets;

import ru.job4j.servlets.persistence.DBStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by User2 on 21.07.2018.
 */
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (DBStore.getInstance().isUserAuthDataValid(login, password)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
            }
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("error", "User auth data invalid");
            doGet(req, resp);
        }
    }
}
