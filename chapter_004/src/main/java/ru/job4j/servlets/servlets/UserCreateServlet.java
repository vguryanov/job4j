package ru.job4j.servlets.servlets;

import ru.job4j.servlets.logic.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User2 on 13.07.2018.
 */
public class UserCreateServlet extends HttpServlet {
    private ValidationService vs = ValidationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html>"
                + "   <body>"
                + "      <form action = '" + req.getContextPath() + "/create' method = 'POST'>"
                + "         Role: "
                + "         <select name='role'>"
                + "             <option value='USER'>User</option>"
                + "             <option value='ADMIN'>Administrator</option>"
                + "         </select>"
                + "         <br>"
                + "         Name: <input type = 'text' name = \"name\">"
                + "         <br>"
                + "         Login: <input type = \"text\" name = \"login\" />"
                + "         <br>"
                + "         Password: <input type = \"password\" name = \"password\" />"
                + "         <br>"
                + "         Email: <input type = \"text\" name = \"email\" />"
                + "         <input type = \"submit\"/>"
                + "      </form>"
                + "   </body>"
                + "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(vs.addUser(name, login, password, email, role)));
        writer.flush();
    }
}
