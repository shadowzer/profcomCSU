<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 21.12.2016
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createCategory</title>
</head>
<body>
${message}<br>
<form method="post" action="/admin/category/create">
    Название категории:<input type="text" name="nameCategory" minlength="5" maxlength="50"/><br>
    <input type="submit" value="Отправить">
</form>
<a href="/admin/category">Назад</a><br>
<a href="/">На главную</a><br>
</body>
</html>
