<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 22.12.2016
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>deleteCategory</title>
</head>
<body>
${message}<br>
<form action="/admin/category/delete" method="post">
    <input type="text" name="nameCategory">
    <input type="submit" value="Удаление по названию">
</form>
<form action="/admin/category/delete" method="post">
    <input type="number" name="id">
    <input type="submit" value="Удаление по ID">
</form>


<a href="/admin/category">Назад</a><br>
<a href="/">На главную</a><br>

</body>
</html>
