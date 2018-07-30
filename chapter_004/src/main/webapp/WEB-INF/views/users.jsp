<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1">
    <thead>
    <td><b>ID</b></td>
    <td><b>Role</b></td>
    <td><b>Name</b></td>
    <td><b>Login</b></td>
    <td><b>Email</b></td>
    </thead>
    <tbody>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <c:out value="${user.id}"></c:out>
            </td>
            <td>
                <c:out value="${user.role}"></c:out>
            </td>
            <td>
                <c:out value="${user.name}"></c:out>
            </td>
            <td>
                <c:out value="${user.login}"></c:out>
            </td>
            <td>
                <c:out value="${user.email}"></c:out>
            </td>

            <c:if test="${role=='ADMIN'||user.login==sessionScope.login}">
                <td>
                    <form action="${pageContext.servletContext.contextPath}/update" method='GET'>
                        <input type="submit" value='update'/>
                        <input type="hidden" id="hidField1" name="id" value="${user.id}">
                        <input type="hidden" id="hidField2" name="name" value="${user.name}">
                        <input type="hidden" id="hidField3" name="login" value="${user.login}">
                        <input type="hidden" id="hidField4" name="password" value="${user.password}">
                        <input type="hidden" id="hidField5" name="email" value="${user.email}">
                        <input type="hidden" id="hidField6" name="role" value="${user.role}">
                    </form>
                </td>
            </c:if>

            <c:if test="${role=='ADMIN'}">
                <td>
                    <form action="${pageContext.servletContext.contextPath}/delete" method='POST'>
                        <input type="submit" value='delete'>
                        <input type="hidden" id="hidField7" name="id" value="${user.id}">
                    </form>
                </td>
            </c:if>

        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
