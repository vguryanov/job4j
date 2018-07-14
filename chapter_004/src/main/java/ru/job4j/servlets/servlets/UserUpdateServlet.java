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
public class UserUpdateServlet extends HttpServlet {
    private ValidationService vs = ValidationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html>"
                + "   <body>"
                + "      <form action = '" + req.getContextPath() + "/update' method = 'POST'>"
                + "         <input type=\"hidden\" id=\"hidField1\" name=\"id\" value=\"" + req.getParameter("id") + "\">"
                + "         Name: <input type = \"text\" name = \"name\" value = '" + req.getParameter("name") + "'>"
                + "         <br>"
                + "         Login: <input type = \"text\" name = \"login\" value = '" + req.getParameter("login") + "'/>"
                + "         <br>"
                + "         Email: <input type = \"text\" name = \"email\" value = '" + req.getParameter("email") + "'/>"
                + "         <input type = \"submit\"/>"
                + "      </form>"
                + "   </body>"
                + "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(vs.updateUser(req)));
        writer.flush();
    }
}
