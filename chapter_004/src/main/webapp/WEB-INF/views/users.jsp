<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Сотрудники</h2>
    <br>
    <table class="table table-striped">
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
                <td style="vertical-align: middle;">
                    <c:out value="${user.id}"></c:out>
                </td>
                <td style="vertical-align: middle;">
                    <c:out value="${user.role}"></c:out>
                </td>
                <td style="vertical-align: middle;">
                    <c:out value="${user.name}"></c:out>
                </td>
                <td style="vertical-align: middle;">
                    <c:out value="${user.login}"></c:out>
                </td>
                <td style="vertical-align: middle;">
                    <c:out value="${user.email}"></c:out>
                </td>

                <c:if test="${role=='ADMIN'||user.login==sessionScope.login}">
                    <td style="vertical-align: middle;">
                        <form action="${pageContext.servletContext.contextPath}/update" method='GET'>
                            <button type="submit" class="btn btn-primary">Update</button>
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
                    <td style="vertical-align: middle;">
                        <form action="${pageContext.servletContext.contextPath}/delete" method='POST'>
                            <button type="submit" class="btn btn-primary">Delete</button>
                            <input type="hidden" id="hidField7" name="id" value="${user.id}">
                        </form>
                    </td>
                </c:if>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
