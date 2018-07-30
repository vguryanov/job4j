package ru.job4j.servlets.servlets;

import ru.job4j.servlets.logic.ValidationService;
import ru.job4j.servlets.persistence.DBStore;
import ru.job4j.servlets.persistence.Store;
import ru.job4j.servlets.persistence.User;

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
    Store<User> store = DBStore.getInstance();
    private ValidationService vs = ValidationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userRole = req.getParameter("role");
        String roleSelect = "Role: "
                + "         <select name='role'>"
                + "             <option value='USER' " + (userRole.equals(User.Role.USER.name()) ? "selected='selected'" : "") + ">User</option>"
                + "             <option value='ADMIN' " + (userRole.equals(User.Role.ADMIN.name()) ? "selected='selected'" : "") + ">Administrator</option>"
                + "         </select>"
                + "         <br>";
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html>"
                + "   <body>"
                + "      <form action = '" + req.getContextPath() + "/update' method = 'POST'>"
                + "         <input type=\"hidden\" id=\"hidField1\" name=\"id\" value=\"" + req.getParameter("id") + "\">"
                + (store.getRoleForLogin((String) req.getSession().getAttribute("login")) == User.Role.ADMIN ? roleSelect : "")
                + "         Name: <input type = \"text\" name = \"name\" value = '" + req.getParameter("name") + "'>"
                + "         <br>"
                + "         Login: <input type = \"text\" name = \"login\" value = '" + req.getParameter("login") + "'/>"
                + "         <br>"
                + "         Password: <input type = \"text\" name = \"password\" value = '" + req.getParameter("password") + "'/>"
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
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(vs.updateUser(id, name, login, password, email, role)));
        writer.flush();
    }
}
