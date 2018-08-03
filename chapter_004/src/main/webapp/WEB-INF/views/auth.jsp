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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
        .container {
            position: relative;
            top: 40%;
            transform: translateY(-50%);
            text-align: center;
            width: 250px;
        }

        div.ex1 {
            margin-bottom: 20px;
        }

        ​
    </style>
</head>
<body>
<div class="container">
    <div class="ex1">
        <h2>Authorization</h2>
    </div>

    <c:if test="${error != ''}">
        <div style="color: red">
            <c:out value="${error}"></c:out>
        </div>
    </c:if>

    <form action=" ${pageContext.servletContext.contextPath}/auth" method="post">
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" class="form-control" placeholder="Enter your login" id="login" name="login"></br>
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter your password" id="password" name="password"><br>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
