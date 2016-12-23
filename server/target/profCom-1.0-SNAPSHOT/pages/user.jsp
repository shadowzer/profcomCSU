<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 20.12.2016
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
    Логин:${login}<br>
    Пароль:${password}<br>
    Имя:${firstname}<br>
    Фамилия:${surname}<br>
    Отчество:${lastname}<br>
    (Не)Бюджет:${budget}<br>
    Форма обучения:${fulltime}<br>
    Табельный номер:${tabnum}<br>
    Студенческая группа:${studentgroup}<br>
    <a href="/admin/user">Назад</a><br>
    <a href="/">На главную</a><br>
</body>
</html>
