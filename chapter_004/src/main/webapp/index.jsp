<%@ page import="ru.job4j.servlets.persistence.MemoryStore" %><%--
  Created by IntelliJ IDEA.
  User: User2
  Date: 16.07.2018
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1">
    <thead>
    <td><b>ID</b></td>
    <td><b>Name</b></td>
    <td><b>Login</b></td>
    <td><b>Email</b></td>
    </thead>
    <tbody>
    <% for (MemoryStore.User u : MemoryStore.getInstance().getAll().values()) {%>
    <tr>
        <td>
            <%=u.getId()%>
        </td>
        <td>
            <%=u.getName()%>
        </td>
        <td>
            <%=u.getLogin()%>
        </td>
        <td>
            <%=u.getEmail()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/update" method='GET'>
                <input type="submit" value='update'/>
                <input type="hidden" id="hidField1" name="id" value="<%=u.getId()%>">
                <input type="hidden" id="hidField2" name="name" value="<%=u.getName()%>">
                <input type="hidden" id="hidField3" name="login" value="<%=u.getLogin()%>">
                <input type="hidden" id="hidField4" name="email" value="<%=u.getEmail()%>">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/delete" method='POST'>
                <input type="submit" value='delete'>
                <input type="hidden" id="hidField5" name="id" value="<%=u.getId()%>">
            </form>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
