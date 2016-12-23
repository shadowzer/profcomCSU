<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 22.12.2016
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateCategory</title>
</head>
<body>
${message}<br>
<form action="/admin/category/update" method="post">
    <input type="number" name="id" value="${id}" readonly/><br>
    <input type="text" name="nameCategory" value="${nameCategory}"><br>
    <input type="submit" value="Сохранить"><br>
</form>
<a href="/admin/category">Назад</a><br>
<a href="/">На главную</a><br>
</body>
</html>
