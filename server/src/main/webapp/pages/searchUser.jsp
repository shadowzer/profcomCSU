<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 20.12.2016
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>searchUser</title>
</head>
<body>
<div>${message}</div>
    <form action="/admin/user/search" method="get">
        <input type="text" name="login">
        <input type="submit" value="Поиск по логину">
    </form>
    <form action="/admin/user/search" method="get">
        <input type="number" name="id">
        <input type="submit" value="Поиск по ID">
    </form>
<a href="/admin/user">Назад</a><br>
<a href="/">На главную</a><br>
</body>
</html>
