package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by User2 on 08.07.2018.
 */
public class UserServlet extends HttpServlet {
    private final LinkedHashMap<Function<String, Boolean>, Function<HttpServletRequest, Boolean>> dispatch = new LinkedHashMap<>();

    {
        dispatch.put(
                s -> s.equals("add"),
                req -> {
                    new User(
                            req.getParameter("name"),
                            req.getParameter("login"),
                            req.getParameter("email")
                    );
                    return true;
                }
        );
        dispatch.put(
                s -> s.equals("update"),
                req -> {
                    User.getUsers().get(Integer.parseInt(req.getParameter("id"))).setName(req.getParameter("name"));
                    return true;
                }
        );
        dispatch.put(
                s -> s.equals("delete"),
                req -> {
                    User.removeUser(req.getParameter("id"));
                    return true;
                }
        );
    }

    private boolean dispatch(HttpServletRequest req) {
        for (Function<String, Boolean> key : dispatch.keySet()) {
            if (key.apply(req.getParameter("action"))) {
                return dispatch.get(key).apply(req);
            }
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User u : User.getUsers().values()) {
            writer.append(u.toString()).append("<br>");
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(dispatch(req)));
        writer.flush();
    }
}
