<%--
  Created by IntelliJ IDEA.
  User: User2
  Date: 21.07.2018
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authorization</title>
</head>
<c:if test="${error != ''}">
    <div style="color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<body>
<form action=" ${pageContext.servletContext.contextPath}/auth" method="post">
    Login: <input type="text" name="login"></br>
    Password: <input type="password" name="password"><br>
    <input type="submit">
</form>
</body>
</html>
